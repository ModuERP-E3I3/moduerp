package com.e3i3.moduerp.attendancerequest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.e3i3.moduerp.attendancerequest.model.dto.AttendanceRequest;
import com.e3i3.moduerp.attendancerequest.model.service.AttendanceRequestService;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;

@Controller
public class AttendanceRequestController {

	@Autowired
	private AttendanceRequestService attendanceRequestService;
	@Autowired
	private EmployeeService employeeService;

	// /attendance/send 경로로 근태 요청 JSP 페이지 연결
	@GetMapping("/attendanceRequest/send.do")
	public String showAttendanceRequestForm(Model model, HttpSession session) {
		// 필요한 데이터를 모델에 추가 (예: 드롭다운에 사용할 값들)
		model.addAttribute("attendanceRequest", new AttendanceRequest());
		String bizNumber = (String) session.getAttribute("biz_number");
		String departmentId = (String) session.getAttribute("departmentId");
		List<Employee> employees = employeeService.selectEmployeesByBizAndDepartment(bizNumber, departmentId);
		model.addAttribute("employees", employees);
		return "attendance/send"; // attendanceRequest 폴더의 send.jsp로 연결
	}

	// 1. 근태 관리 요청 제출 (임시 저장 없이 바로 제출)
	@PostMapping("/attendanceRequest/submit.do")
	public String submitAttendanceRequest(HttpSession session, @ModelAttribute AttendanceRequest request, Model model) {
		// 디버깅을 위해 approver 값 출력
		System.out.println("Approver: " + request.getApprover());

		request.setAttendancerequestId(UUID.randomUUID().toString());
		request.setUuid((String) session.getAttribute("uuid"));
		request.setBizNumber((String) session.getAttribute("biz_number"));
		int result = attendanceRequestService.insertAttendanceRequest(request);

		if (result > 0) {
			model.addAttribute("message", "근태신청이 성공적으로 등록되었습니다!");
			model.addAttribute("redirectUrl", "/attendanceRequest/mylist.do"); // 본인이 신청한 근태 리스트로 이동하는 url 
		} else {
			model.addAttribute("message", "근태신청의 제출을 실패했습니다.");
			model.addAttribute("redirectUrl", "/attendanceRequest/send.do"); // 실패 시 다시 폼 페이지로
		}

		return "attendance/submitResult";
	}

	// 2. 근태 관리 요청 임시 저장
	@PostMapping("/attendanceRequest/save")
	public String saveAttendanceRequest(@ModelAttribute AttendanceRequest request, Model model) {
		int result = attendanceRequestService.insertSavedAttendanceRequest(request);
		model.addAttribute("message", result > 0 ? "Request saved successfully!" : "Save failed.");
		return "attendance/saveResult";
	}

	// 3. 상태 업데이트 (임시 저장에서 최종 제출로 변경)
	@PostMapping("/attendanceRequest/updateStatus")
	public ResponseEntity<String> updateRequestStatus(@RequestParam("id") String attendanceRequestId,
			@RequestParam("status") String status) {
		Map<String, Object> params = new HashMap<>();
		params.put("attendanceRequestId", attendanceRequestId);
		params.put("status", status);

		int result = attendanceRequestService.updateRequestStatus(params);
		return ResponseEntity.ok(result > 0 ? "Status updated successfully." : "Status update failed.");
	}

	// 4. 특정 근태 관리 요청 ID로 조회
	@GetMapping("/attendanceRequest/view/{id}")
	public String viewAttendanceRequestById(@PathVariable("id") String attendanceRequestId, Model model) {
		AttendanceRequest request = attendanceRequestService.selectAttendanceRequestById(attendanceRequestId);
		model.addAttribute("request", request);
		return "attendanc/view";
	}

	// 5. 특정 사용자 UUID로 근태 관리 요청 조회
	@GetMapping("/attendanceRequest/mylist.do")
	public String getAttendanceRequestsByUuid(HttpSession session, Model model) {
		 String uuid = (String) session.getAttribute("uuid");
		List<AttendanceRequest> requests = attendanceRequestService.selectAttendanceRequestByUuid(uuid);
		model.addAttribute("requests", requests);
		return "attendance/mylist";
	}

	// 6. 특정 사업자번호(BizNumber)로 근태 관리 요청 조회
	@GetMapping("/attendanceRequest/company/{bizNumber}")
	public String getAttendanceRequestsByBizNumber(@PathVariable("bizNumber") String bizNumber, Model model) {
		List<AttendanceRequest> requests = attendanceRequestService.selectAttendanceRequestsByBizNumber(bizNumber);
		model.addAttribute("requests", requests);
		return "attendance/list";
	}

	// 7. 특정 사용자 UUID와 신청 유형으로 근태 관리 요청 조회
	@GetMapping("/attendanceRequest/user/{uuid}/type/{applicationType}")
	public String getAttendanceRequestsByUuidAndType(@PathVariable("uuid") String uuid,
			@PathVariable("applicationType") String applicationType, Model model) {
		Map<String, Object> params = new HashMap<>();
		params.put("uuid", uuid);
		params.put("applicationType", applicationType);
		List<AttendanceRequest> requests = attendanceRequestService.selectByUUIDAndApplicationType(params);
		model.addAttribute("requests", requests);
		return "attendance/list";
	}

	// 8. 결재자 UUID로 대기 중인 근태 관리 요청 조회
	@GetMapping("/attendanceRequest/approver/{approver}")
	public String getPendingRequestsByApprover(@PathVariable("approver") String approver, Model model) {
		List<AttendanceRequest> requests = attendanceRequestService.selectPendingRequestsByApprover(approver);
		model.addAttribute("requests", requests);
		return "attendance/pendingList";
	}

	// 9. 특정 사용자 UUID와 날짜 범위로 근태 관리 요청 조회
	@GetMapping("/attendanceRequest/user/{uuid}/dateRange")
	public String getAttendanceRequestsByUuidAndDateRange(@PathVariable("uuid") String uuid,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, Model model) {
		Map<String, Object> params = new HashMap<>();
		params.put("uuid", uuid);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		List<AttendanceRequest> requests = attendanceRequestService.selectByUUIDAndDateRange(params);
		model.addAttribute("requests", requests);
		return "attendance/list";
	}

	// 10. 전체 근태 관리 요청 조회
	@GetMapping("/attendanceRequest/all")
	public String getAllAttendanceRequests(Model model) {
		List<AttendanceRequest> requests = attendanceRequestService.selectAllAttendanceRequests();
		model.addAttribute("requests", requests);
		return "attendance/list";
	}

	// 11. 근태 관리 요청 업데이트
	@PostMapping("/attendanceRequest/update")
	public String updateAttendanceRequest(@ModelAttribute AttendanceRequest request, Model model) {
		int result = attendanceRequestService.updateAttendanceRequest(request);
		model.addAttribute("message", result > 0 ? "Request updated successfully!" : "Update failed.");
		return "attendance/updateResult";
	}

	// 12. 특정 UUID로 근태 관리 요청 삭제
	@PostMapping("/attendanceRequest/delete/uuid/{uuid}")
	public String deleteAttendanceRequestByUuid(@PathVariable("uuid") String uuid, Model model) {
		int result = attendanceRequestService.deleteAttendanceRequestByUuid(uuid);
		model.addAttribute("message", result > 0 ? "Request deleted successfully!" : "Delete failed.");
		return "attendance/deleteResult";
	}

	// 13. 특정 근태 관리 요청 ID로 삭제
	@PostMapping("/attendanceRequest/delete/{id}")
	public String deleteAttendanceRequestById(@PathVariable("id") String attendanceRequestId, Model model) {
		int result = attendanceRequestService.deleteAttendanceRequestById(attendanceRequestId);
		model.addAttribute("message", result > 0 ? "Request deleted successfully!" : "Delete failed.");
		return "attendance/deleteResult";
	}
}
