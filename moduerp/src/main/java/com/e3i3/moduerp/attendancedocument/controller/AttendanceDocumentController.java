package com.e3i3.moduerp.attendancedocument.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.e3i3.moduerp.attendancedocument.model.dto.AttendanceDocument;
import com.e3i3.moduerp.attendancedocument.model.service.AttendanceDocumentService;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;

@Controller
public class AttendanceDocumentController {

	@Autowired
	private AttendanceDocumentService attendanceRequestService;
	@Autowired
	private EmployeeService employeeService;

	// 필드 추가
	@Value("${email.upload.dir}")
	private String uploadDir;

	// /attendance/send 경로로 근태 요청 JSP 페이지 연결
	@GetMapping("/attendanceDocument/send.do")
	public String showAttendanceRequestForm(
			@RequestParam(value = "attendancerequestId", required = false) String attendancerequestId, Model model,
			HttpSession session) {
		// 새로운 신청일 경우
		AttendanceDocument attendanceDocument = new AttendanceDocument();

		if (attendancerequestId != null && !attendancerequestId.isEmpty()) {
			// 기존 근태 신청을 수정할 경우, 해당 ID로 데이터 조회
			attendanceDocument = attendanceRequestService.selectAttendanceRequestById(attendancerequestId);
		}

		// 날짜 포맷 맞추기 (yyyy-MM-dd 형식으로 변환)
		if (attendanceDocument.getStartDate() != null && attendanceDocument.getStartDate().length() > 10) {
			attendanceDocument.setStartDate(attendanceDocument.getStartDate().substring(0, 10));
		}
		if (attendanceDocument.getEndDate() != null && attendanceDocument.getEndDate().length() > 10) {
			attendanceDocument.setEndDate(attendanceDocument.getEndDate().substring(0, 10));
		}

		// 기본 데이터 설정
		model.addAttribute("attendanceDocument", attendanceDocument);
		String bizNumber = (String) session.getAttribute("biz_number");
		String departmentId = (String) session.getAttribute("departmentId");
		List<Employee> employees = employeeService.selectEmployeesByBizAndDepartment(bizNumber, departmentId);
		model.addAttribute("employees", employees);
		return "attendance/send"; // attendanceRequest 폴더의 send.jsp로 연결
	}

	// 1. 근태 관리 요청 제출 (임시 저장 없이 바로 제출)
	@PostMapping("/attendanceDocument/submit.do")
	public String submitAttendanceRequest(@RequestParam(value="file", required = false) MultipartFile file, HttpSession session,
			@ModelAttribute AttendanceDocument request, Model model, RedirectAttributes redirectAttributes) {
		// 디버깅을 위해 approver 값 출력
		System.out.println("Approver: " + request.getApprover());

		String uuid = (String) session.getAttribute("uuid");

		// 결재자 조회
		Employee approver = employeeService.selectEmployeeByUuid(request.getApprover());

		if (approver == null) {
			model.addAttribute("message", "유효하지 않은 결재자입니다.");
			return "attendance/send"; // 신청 폼 페이지로 이동
		}

		System.out.println("*********결재자 이름: " + approver.getEmpName());

		request.setAttendancerequestId(UUID.randomUUID().toString());
		request.setApproverName(approver.getEmpName()); // 결재자 이름을 DTO에 저장
		request.setUuid((String) session.getAttribute("uuid"));
		// 요청자 조회
		Employee requester = employeeService.selectEmployeeByUuid(request.getUuid());
		request.setRequesterName(requester.getEmpName()); // 요청자 이름을 DTO에 저장
		request.setBizNumber((String) session.getAttribute("biz_number"));

		// 파일 첨부 처리 로직
		if (!file.isEmpty()) {
			try {
				// 원본 파일명 가져오기
				String originalFilename = file.getOriginalFilename();

				if (originalFilename == null) {
					model.addAttribute("message", "파일명 정보를 가져올 수 없습니다.");
					return "attendance/send";
				}

				// 파일명에 ".." 포함 여부 확인 (디렉토리 트래버설 방지)
				if (originalFilename.contains("..")) {
					model.addAttribute("message", "유효하지 않은 파일명입니다.");
					return "attendance/send";
				}

				// 새로운 파일명 생성 (첨부한 사람의 UUID + "_" + 파일명)
				String newFilename = uuid + "_" + originalFilename;

				// 파일 저장 경로 설정 (Java Config의 uploadDir 사용)
				Path filePath = Paths.get(uploadDir, newFilename);

				// 디렉토리가 존재하지 않으면 생성
				Path uploadDirPath = filePath.getParent();
				if (!Files.exists(uploadDirPath)) {
					Files.createDirectories(uploadDirPath);
				}

				// 파일 저장
				file.transferTo(filePath.toFile());

				// 파일 저장 확인
				if (Files.exists(filePath)) {
					System.out.println("파일이 성공적으로 저장되었습니다: " + filePath.toString());
				} else {
					System.out.println("파일 저장에 실패했습니다.");
				}

				// attachmentPath 설정 (파일명만 저장)
				request.setAttachment(newFilename);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
				return "attendance/send"; // 오류 발생 시 전송 페이지로 돌아감
			}
		} else {
			request.setAttachment(null); // 파일 첨부하지 않은 경우
		}

		int result = attendanceRequestService.insertAttendanceRequest(request);

		// 근태 신청이 성공적으로 등록되었으면 바로 /attendanceRequest/mylist.do 로 리다이렉트
		if (result > 0) {
			redirectAttributes.addFlashAttribute("message", "근태신청의 제출을 성공했습니다.");
			return "redirect:/attendanceDocument/mylist.do"; // 바로 근태 리스트 페이지로 리다이렉트
		} else {
			// 실패 시 다시 신청 폼 페이지로 이동
			model.addAttribute("message", "근태신청의 제출을 실패했습니다.");
			return "attendance/send"; // 신청 폼 페이지로 이동
		}
	}

	// 2. 근태 신청 임시 저장
	@PostMapping("/attendanceDocument/save.do")
	public String saveAttendanceRequest(@RequestParam(value="file", required = false) MultipartFile file , HttpSession session,
			@ModelAttribute AttendanceDocument request, Model model, RedirectAttributes redirectAttributes)
			throws IOException {

		String uuid = (String) session.getAttribute("uuid");

		if (request.getApplicationType() == null || request.getApplicationType().isEmpty()) {
			model.addAttribute("message", "신청 유형이 누락되었습니다.");
			return "attendance/send"; // 신청 폼 페이지로 이동
		}

		// 결재자 조회
		Employee approver = employeeService.selectEmployeeByUuid(request.getApprover());
		if (approver == null) {
			model.addAttribute("message", "유효하지 않은 결재자입니다.");
			return "attendance/send"; // 신청 폼 페이지로 이동
		}

		System.out.println("***********결재자 이름: " + approver.getEmpName());

		System.out.println("파일: "+file);
		
		
		// 기본 정보 설정
		request.setAttendancerequestId(UUID.randomUUID().toString());
		request.setApproverName(approver.getEmpName()); // 결재자 이름을 DTO에 저장
		request.setBizNumber((String) session.getAttribute("biz_number"));
		request.setUuid(uuid);

		// 요청자 조회
		Employee requester = employeeService.selectEmployeeByUuid(request.getUuid());
		request.setRequesterName(requester.getEmpName()); // 요청자 이름을 DTO에 저장

		// 파일 첨부 처리 로직
				if (!file.isEmpty()) {
					try {
						// 원본 파일명 가져오기
						String originalFilename = file.getOriginalFilename();

						if (originalFilename == null) {
							model.addAttribute("message", "파일명 정보를 가져올 수 없습니다.");
							return "attendance/send";
						}

						// 파일명에 ".." 포함 여부 확인 (디렉토리 트래버설 방지)
						if (originalFilename.contains("..")) {
							model.addAttribute("message", "유효하지 않은 파일명입니다.");
							return "attendance/send";
						}

						// 새로운 파일명 생성 (첨부한 사람의 UUID + "_" + 파일명)
						String newFilename = uuid + "_" + originalFilename;

						// 파일 저장 경로 설정 (Java Config의 uploadDir 사용)
						Path filePath = Paths.get(uploadDir, newFilename);

						// 디렉토리가 존재하지 않으면 생성
						Path uploadDirPath = filePath.getParent();
						if (!Files.exists(uploadDirPath)) {
							Files.createDirectories(uploadDirPath);
						}

						// 파일 저장
						file.transferTo(filePath.toFile());

						// 파일 저장 확인
						if (Files.exists(filePath)) {
							System.out.println("파일이 성공적으로 저장되었습니다: " + filePath.toString());
						} else {
							System.out.println("파일 저장에 실패했습니다.");
						}

						// attachmentPath 설정 (파일명만 저장)
						request.setAttachment(newFilename);
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
						return "attendance/send"; // 오류 발생 시 전송 페이지로 돌아감
					}
				} else {
					request.setAttachment(null); // 파일 첨부하지 않은 경우
				}


		int result = attendanceRequestService.insertSavedAttendanceRequest(request); // Insert 메소드 호출
		redirectAttributes.addFlashAttribute("message", "근태신청이 성공적으로 저장되었습니다.");

		if (result > 0) {
			return "redirect:/attendanceDocument/mylist.do"; // 성공 시 리스트 페이지로 리다이렉트
		} else {
			// 실패 시 다시 신청 폼 페이지로 이동
			model.addAttribute("message", "근태신청의 임시저장을 실패했습니다.");
			return "attendance/send"; // 신청 폼 페이지로 이동
		}
	}

	// 3. 상태 업데이트 (임시 저장에서 최종 제출로 변경 & isApproved가 'N'에서 '대기'로 변경)
	@PostMapping("/attendanceDocument/updateStatus.do")
	@ResponseBody
	public ResponseEntity<Map<String, String>> updateRequestStatus(@RequestParam("id") String attendanceRequestId,
			@RequestParam("status") String status, @RequestParam("approver") String approverUUID,
			@RequestParam("isApproved") String isApproved) {

		Map<String, Object> params = new HashMap<>();
		System.out.println(" 아이디 : " + attendanceRequestId);

		params.put("attendanceRequestId", attendanceRequestId);
		params.put("status", status);
		params.put("approver", approverUUID);
		params.put("isApproved", isApproved);

		int result = attendanceRequestService.updateRequestStatus(params);
		System.out.println("업뎃 된 reulst 수: " + result);

		Map<String, String> response = new HashMap<>();
		if (result > 0 && "제출완료".equals(status) && "대기".equals(isApproved)) {
			response.put("redirectUrl", "/attendanceDocument/mylist.do");
		} else {
			response.put("error", "제출에 실패했습니다. 다시 시도해주세요.");
			response.put("redirectUrl", "/attendanceDocument/detail/" + attendanceRequestId + ".do");
		}

		return ResponseEntity.ok(response);
	}

	// 4. 특정 근태 신청서의 상세 보기
	@GetMapping("/attendanceDocument/detail/{attendancerequestId}.do")
	public String getAttendanceRequestDetail(HttpSession session,
			@PathVariable("attendancerequestId") String attendancerequestId, Model model) {
		AttendanceDocument request = attendanceRequestService.selectAttendanceRequestById(attendancerequestId);
		String uuid = (String) session.getAttribute("uuid");
		model.addAttribute("loginUser", uuid);
		model.addAttribute("request", request);
		return "attendance/detail";
	}

	// 5. 특정 사용자 UUID로 결재 목록 조회
	@GetMapping("/attendanceDocument/mylist.do")
	public String getAttendanceDocumentsByUuid(HttpServletRequest request, HttpSession session, Model model) {
		String uuid = (String) session.getAttribute("uuid");

		// 버전 파라미터를 받아옴
		String versionParam = request.getParameter("version");
		Long lastVersion = (versionParam != null) ? Long.parseLong(versionParam) : 0L;

		// 현재 시간 기준으로 버전 설정
		long currentVersion = System.currentTimeMillis();

		// 새로운 데이터가 있는지 확인
		if (lastVersion < currentVersion) {
			// 내가 요청한 결재 리스트
			List<AttendanceDocument> documents = attendanceRequestService.selectAttendanceRequestByUuid(uuid);

			// 내가 승인해야 하는 결재 리스트
			List<AttendanceDocument> requests = attendanceRequestService.selectPendingRequestsByApprover(uuid);

			// '대기' 상태인 요청 문서 필터링
			List<AttendanceDocument> pendingApprovalRequests = requests.stream()
					.filter(doc -> "대기".equals(doc.getIsApproved())).collect(Collectors.toList());

			model.addAttribute("pendingApprovalRequests", pendingApprovalRequests);

			// '승인'이거나 '반려'된 요청 문서 필터링
			List<AttendanceDocument> approvedOrRejected_r = requests.stream()
					.filter(doc -> "승인".equals(doc.getIsApproved()) || "반려".equals(doc.getIsApproved()))
					.collect(Collectors.toList());

			model.addAttribute("approvedOrRejected_r", approvedOrRejected_r);

			// '승인' 또는 '반려'가 아닌 문서 필터링
			List<AttendanceDocument> notYetProcessed = documents.stream()
					.filter(doc -> !"승인".equals(doc.getIsApproved()) && !"반려".equals(doc.getIsApproved()))
					.collect(Collectors.toList());

			model.addAttribute("notYetProcessed", notYetProcessed);

			// '승인'이거나 '반려'된 문서 필터링
			List<AttendanceDocument> approvedOrRejected = documents.stream()
					.filter(doc -> "승인".equals(doc.getIsApproved()) || "반려".equals(doc.getIsApproved()))
					.collect(Collectors.toList());

			model.addAttribute("approvedOrRejected", approvedOrRejected);

			model.addAttribute("version", currentVersion); // 새로운 버전을 model에 추가
		}

		return "attendance/mylist";
	}

	@GetMapping("/data/pendingApprovalRequests.do")
	@ResponseBody
	public List<AttendanceDocument> getPendingApprovalRequests(HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		List<AttendanceDocument> requests = attendanceRequestService.selectPendingRequestsByApprover(uuid);
		return requests.stream().filter(doc -> "대기".equals(doc.getIsApproved())).collect(Collectors.toList());
	}

	@GetMapping("/data/approvedOrRejected_r.do")
	@ResponseBody
	public List<AttendanceDocument> getApprovedOrRejected_r(HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		List<AttendanceDocument> requests = attendanceRequestService.selectPendingRequestsByApprover(uuid);
		return requests.stream().filter(doc -> "승인".equals(doc.getIsApproved()) || "반려".equals(doc.getIsApproved()))
				.collect(Collectors.toList());
	}

	@GetMapping("/data/notYetProcessed.do")
	@ResponseBody
	public List<AttendanceDocument> getNotYetProcessed(HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		List<AttendanceDocument> documents = attendanceRequestService.selectAttendanceRequestByUuid(uuid);
		return documents.stream().filter(doc -> !"승인".equals(doc.getIsApproved()) && !"반려".equals(doc.getIsApproved()))
				.collect(Collectors.toList());
	}

	@GetMapping("/data/approvedOrRejected.do")
	@ResponseBody
	public List<AttendanceDocument> getApprovedOrRejected(HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		List<AttendanceDocument> documents = attendanceRequestService.selectAttendanceRequestByUuid(uuid);
		return documents.stream().filter(doc -> "승인".equals(doc.getIsApproved()) || "반려".equals(doc.getIsApproved()))
				.collect(Collectors.toList());
	}

	// 6. 특정 사업자번호(BizNumber)로 근태 관리 요청 조회
	@GetMapping("/attendanceDocument/company/{bizNumber}")
	public String getAttendanceRequestsByBizNumber(@PathVariable("bizNumber") String bizNumber, Model model) {
		List<AttendanceDocument> requests = attendanceRequestService.selectAttendanceRequestsByBizNumber(bizNumber);
		model.addAttribute("documents", requests);
		return "attendance/list";
	}

	// 7. 특정 사용자 UUID와 신청 유형으로 근태 관리 요청 조회
	@GetMapping("/attendanceDocument/user/{uuid}/type/{applicationType}")
	public String getAttendanceRequestsByUuidAndType(@PathVariable("uuid") String uuid,
			@PathVariable("applicationType") String applicationType, Model model) {
		Map<String, Object> params = new HashMap<>();
		params.put("uuid", uuid);
		params.put("applicationType", applicationType);
		List<AttendanceDocument> requests = attendanceRequestService.selectByUUIDAndApplicationType(params);
		model.addAttribute("documents", requests);
		return "attendance/list";
	}

	// 8. 결재자 UUID로 대기 중인 근태 관리 요청 조회
	@GetMapping("/attendanceDocument/approver/{approver}")
	public String getPendingRequestsByApprover(@PathVariable("approver") String approver, Model model) {
		List<AttendanceDocument> requests = attendanceRequestService.selectPendingRequestsByApprover(approver);
		model.addAttribute("documents", requests);
		return "attendance/pendingList";
	}

	// 9. 특정 사용자 UUID와 날짜 범위로 근태 관리 요청 조회
	@GetMapping("/attendanceDocument/user/{uuid}/dateRange")
	public String getAttendanceRequestsByUuidAndDateRange(@PathVariable("uuid") String uuid,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, Model model) {
		Map<String, Object> params = new HashMap<>();
		params.put("uuid", uuid);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		List<AttendanceDocument> requests = attendanceRequestService.selectByUUIDAndDateRange(params);
		model.addAttribute("documents", requests);
		return "attendance/list";
	}

	// 10. 전체 근태 관리 요청 조회
	@GetMapping("/attendanceDocument/all")
	public String getAllAttendanceRequests(Model model) {
		List<AttendanceDocument> requests = attendanceRequestService.selectAllAttendanceRequests();
		model.addAttribute("documents", requests);
		return "attendance/list";
	}

	// 11. 근태 관리 요청 업데이트
	@PostMapping("/attendanceDocument/update.do")
	public String updateAttendanceRequest(@RequestParam("attendancerequestId") String attendancerequestId,
			@ModelAttribute AttendanceDocument attendanceDocument, RedirectAttributes redirectAttributes) {

		// 기존 객체 조회
		AttendanceDocument existingDocument = attendanceRequestService.selectAttendanceRequestById(attendancerequestId);

		if (existingDocument != null) {
			// 기존 데이터에 덮어쓰기
			attendanceDocument.setAttendancerequestId(attendancerequestId); // ID 유지
			// 기존 객체의 값을 수정
			attendanceRequestService.updateAttendanceRequest(attendanceDocument);
			redirectAttributes.addFlashAttribute("successMessage", "근태 문서가 성공적으로 수정되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "문서를 찾을 수 없습니다.");
		}

		// 수정 후 목록 페이지로 리다이렉트
		return "redirect:/attendanceDocument/mylist.do";
	}

	// 12. 특정 UUID로 근태 관리 요청 삭제
	@PostMapping("/attendanceDocument/delete/uuid/{uuid}")
	public String deleteAttendanceRequestByUuid(@PathVariable("uuid") String uuid, Model model) {
		int result = attendanceRequestService.deleteAttendanceRequestByUuid(uuid);
		model.addAttribute("message", result > 0 ? "Request deleted successfully!" : "Delete failed.");
		return "attendance/deleteResult";
	}

	// 13. 특정 근태 관리 요청 ID로 삭제
	@GetMapping("/attendanceDocument/cancel.do")
	public String deleteAttendanceRequestById(@RequestParam("attendancerequestId") String attendanceRequestId,
			Model model) {
		int result = attendanceRequestService.deleteAttendanceRequestById(attendanceRequestId);
		model.addAttribute("message", result > 0 ? "성공적으로 삭제되었습니다!" : "삭제 실패했습니다.");
		return "redirect:/attendanceDocument/mylist.do";
	}

	// 14. 특정 근태 관리 요청의 승인 여부 '승인'으로 업데이트
	@PostMapping("/attendanceDocument/approve.do")
	public String approveAttendanceRequest(@RequestParam("attendancerequestId") String attendancerequestId,
			RedirectAttributes redirectAttributes) {
		System.out.println("승인 확인 id: " + attendancerequestId);
		try {
			// 승인 상태 업데이트 서비스 호출
			int result = attendanceRequestService.updateApprovalStatus(attendancerequestId);

			// 결과 처리
			if (result > 0) {
				redirectAttributes.addFlashAttribute("successMessage", "결재를 승인처리했습니다.");
			} else {
				redirectAttributes.addFlashAttribute("errorMessage", "결재에 실패했습니다. 다시 시도해주세요.");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "처리 중 오류가 발생했습니다.");
		}

		return "redirect:/attendanceDocument/mylist.do";
	}

	// 15. 결재자가 근태 요청의 승인 여부를 '반려'로 업데이트
	@PostMapping("/attendanceDocument/reject.do")
	@ResponseBody
	public ResponseEntity<Map<String, String>> rejectRequest(
			@RequestParam("attendancerequestId") String attendancerequestId) {
		Map<String, String> response = new HashMap<>();
		System.out.println("승인 확인 id: " + attendancerequestId);
		try {
			// 승인 상태 업데이트 서비스 호출
			int result = attendanceRequestService.rejectRequest(attendancerequestId);

			// 결과 처리
			if (result > 0) {
				response.put("redirectUrl", "/attendanceDocument/mylist.do");
			} else {
				response.put("error", "결재에 실패했습니다. 다시 시도해주세요.");
			}
		} catch (Exception e) {
			response.put("error", "처리 중 오류가 발생했습니다.");
		}

		return ResponseEntity.ok(response);
	}

	// 16. 특정 근태의 반려를 취소하는 메소드 (반려 -> 대기)
	@PostMapping("/attendanceDocument/undoReject.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> undoRejectRequest(
			@RequestParam("attendancerequestId") String attendancerequestId) {
		Map<String, Object> response = new HashMap<>();
		try {
			// 승인 상태를 '대기'로 업데이트하는 서비스 호출
			int result = attendanceRequestService.undoRejectRequest(attendancerequestId);

			if (result > 0) {
				response.put("redirectUrl", "/attendanceDocument/mylist.do");
			} else {
				response.put("error", "반려 취소에 실패했습니다. 다시 시도해주세요.");
			}
		} catch (Exception e) {
			response.put("error", "처리 중 오류가 발생했습니다.");
		}
		return ResponseEntity.ok(response);
	}

}
