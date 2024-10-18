package com.e3i3.moduerp.email.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.e3i3.moduerp.company.model.service.CompanyService;
import com.e3i3.moduerp.email.model.dto.Email;
import com.e3i3.moduerp.email.model.service.EmailService;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.dto.EmployeeBasicInfo;
import com.e3i3.moduerp.employee.model.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@PropertySource("classpath:application.properties") // 프로퍼티 파일 명시적 로드
@Controller
public class EmailController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmployeeService employeeService; // EmployeeService 추가하여 UUID 조회
	
	// 필드 추가
	@Value("${email.upload.dir}")
	private String uploadDir;

	// 이메일 전송 페이지로 이동
	@GetMapping("/email/send.do")
	public String sendEmailPage() {
		return "email/sendEmail"; // 전송 페이지 뷰 이름
	}

	@GetMapping(value = "/email/searchRecipient.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String searchEmailsByBizNumber(@RequestParam("keyword") String keyword, HttpSession session) {
		String loginUUID = (String) session.getAttribute("uuid");

		if (loginUUID == null) {
			// 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
			return "redirect:/"; // 로그인 페이지 URL로 변경 필요
		}
		String bizNumber = (String) session.getAttribute("biz_number");

		List<EmployeeBasicInfo> result = employeeService.selectEmployeesByEmailAndBizNumber(keyword, bizNumber).stream()
				.map(emp -> new EmployeeBasicInfo(emp.getEmpName(), emp.getEmpEmail())).collect(Collectors.toList());

		// ObjectMapper를 사용하여 리스트를 JSON 문자열로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "[]"; // 예외 발생 시 빈 배열 반환
		}
	}

	// 이메일 전송 처리
	@PostMapping("/email/sending.do")
	public String sendEmail(@ModelAttribute Email email, @RequestParam("file") MultipartFile file, HttpSession session,
			HttpServletRequest request, Model model) throws IOException {
		// 1. 로그인 유저의 이메일 주소와 UUID, 사업자번호 가져오기
		String loginUUID = (String) session.getAttribute("uuid");

		if (loginUUID == null) {
			// 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
			return "redirect:/"; // 로그인 페이지 URL로 변경 필요
		}
		String senderEmail = (String) session.getAttribute("email");
		String bizNumber = (String) session.getAttribute("biz_number"); // 사업자번호 가져오기

		email.setSenderUUID(loginUUID);
		email.setSenderEmail(senderEmail);

		// 2. 수신자의 이메일로 UUID 조회 (EmployeeService를 사용하여 조회)
		String recipientEmail = email.getRecipientEmail();
		Employee recipient = employeeService.selectEmployeeByEmailAndBizNumber(recipientEmail, bizNumber);

		if (recipient == null) {
			model.addAttribute("message", "수신자의 이메일을 찾을 수 없습니다. 같은 회사의 이메일만 입력해 주세요.");
			return "email/error"; // 수신자 이메일을 찾지 못하면 다시 전송 페이지로 이동
		}

		// 3. 수신자의 UUID 설정
		email.setRecipientUUID(recipient.getUuid().toString());
		email.setRecipientName(recipient.getEmpName()); // 수신자 이름도 설정

		// 4. 현재 시간 설정
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		Timestamp sentDate = Timestamp.valueOf(now);
		// 분 단위의 등록시간을 sendDate로 설정하기
		email.setSentDate(sentDate);

		// 파일 첨부 처리 로직
		if (!file.isEmpty()) {
			try {
				// 원본 파일명 가져오기
				String originalFilename = file.getOriginalFilename();

				if (originalFilename == null) {
					model.addAttribute("message", "파일명 정보를 가져올 수 없습니다.");
					return "email/error";
				}

				// 파일명에 ".." 포함 여부 확인 (디렉토리 트래버설 방지)
				if (originalFilename.contains("..")) {
					model.addAttribute("message", "유효하지 않은 파일명입니다.");
					return "email/error";
				}

				// 새로운 파일명 생성 (첨부한 사람의 UUID + "_" + 파일명)
				String newFilename = loginUUID + "_" + originalFilename;

				// 파일 저장 경로 설정 시 File.separator 사용
				String uploadDir = this.uploadDir.replace("/", File.separator).replace("\\", File.separator);

				// 로그 추가 (디버깅용)
				System.out.println("Upload Directory: " + uploadDir);

				// 디렉토리가 존재하지 않으면 생성
				File dir = new File(uploadDir);
				if (!dir.exists()) {
					boolean dirsCreated = dir.mkdirs();
					if (!dirsCreated) {
						model.addAttribute("message", "파일 저장 디렉토리를 생성할 수 없습니다.");
						return "email/error";
					}
				}

				// 파일 저장 경로를 File 객체로 생성
				File serverFile = new File(uploadDir + File.separator + newFilename);

				// 경로 로그 출력 (확인용)
				System.out.println("통일된 파일 저장 경로: " + serverFile.getAbsolutePath());

				// 파일 저장
				file.transferTo(serverFile);

				// 파일 저장 경로 확인
				if (serverFile.exists()) {
					System.out.println("파일이 성공적으로 저장되었습니다: " + serverFile.getAbsolutePath());
				} else {
					System.out.println("파일 저장에 실패했습니다.");
				}

				// 파일 경로가 Windows 또는 Linux 환경에 따라 다르게 처리될 수 있으므로,
				String normalizedPath = serverFile.getAbsolutePath().replace("\\", "/");
				System.out.println("정규화된 파일 경로: " + normalizedPath);

				// attachmentPath 설정 (파일명만 저장)
				email.setAttachmentPath(newFilename);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
				return "email/error"; // 오류 발생 시 이메일 전송 페이지로 돌아감
			}
		} else {
			email.setAttachmentPath(null); // 파일 첨부하지 않은 경우
		}

		// 이메일 저장
		emailService.insertEmail(email);
		model.addAttribute("message", "이메일이 전송되었습니다.");

		return "redirect:/email/sent.do"; // 전송 완료 페이지로 리다이렉트
	}

	@GetMapping("/email/inbox.do")
	public String showReceivedEmails(@RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpSession session) {
	    String loginUUID = (String) session.getAttribute("uuid");

	    if (loginUUID == null) {
	        // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
	        return "redirect:/"; 
	    }

	    // 한 페이지당 표시할 이메일 수
	    int pageSize = 10;

	    // 페이징을 위한 offset 계산
	    int offset = (page - 1) * pageSize;

	    // 총 수신 이메일 수 조회
	    int totalEmails = emailService.countEmailsByRecipient(loginUUID);

	    // 총 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) totalEmails / pageSize);

	    // 해당 페이지의 이메일 목록 조회
	    List<Email> emails = emailService.selectEmailsByRecipientWithPaging(loginUUID, offset, pageSize);

	    // 모델에 데이터 추가
	    model.addAttribute("emails", emails);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalEmails", totalEmails);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPages", totalPages);

	    return "email/inbox";
	}


	// 보낸 이메일 목록 조회 (페이징 처리)
	@GetMapping("/email/sent.do")
	public String showSentEmails(@RequestParam(value = "page", defaultValue = "1") int page, HttpSession session,
	                             Model model) {
	    String loginUUID = (String) session.getAttribute("uuid");

	    if (loginUUID == null) {
	        return "redirect:/"; // 로그인하지 않은 경우 메인 페이지로 리다이렉트
	    }

	    // 한 페이지당 표시할 이메일 수
	    int pageSize = 10;

	    // 페이징을 위한 offset 계산
	    int offset = (page - 1) * pageSize;

	    // 총 보낸 이메일 수 조회
	    int totalEmails = emailService.countEmailsBySender(loginUUID);

	    // 총 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) totalEmails / pageSize);

	    // 해당 페이지의 보낸 이메일 목록 조회
	    List<Email> emails = emailService.selectEmailsBySenderWithPaging(loginUUID, offset, pageSize);

	    // 모델에 페이징 및 이메일 데이터 추가
	    model.addAttribute("emails", emails);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalEmails", totalEmails);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPages", totalPages);

	    return "email/sent"; // sent.jsp 페이지로 포워딩
	}


	/**
	 * 이메일 상세 보기 URL: /email/view.do?emailId=XXX
	 */
	@SuppressWarnings("unlikely-arg-type")
	@GetMapping("/email/view.do") // GET 메소드로 명시적으로 지정
	public String viewEmail(@RequestParam("emailId") Long emailId, Model model, HttpSession session) {
		String loginUUID = (String) session.getAttribute("uuid");

		if (loginUUID == null) {
			// 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
			return "redirect:/"; // 로그인 페이지 URL로 변경 필요
		}

		// 이메일 조회
		Email email = emailService.selectEmailById(emailId);

		if (email == null) {
			// 이메일이 존재하지 않는 경우, 오류 페이지로 포워드
			model.addAttribute("errorMessage", "해당 이메일을 찾을 수 없습니다.");
			return "email/error"; // error.jsp로 포워딩 (필요 시 생성)
		}

		// 현재 사용자가 이메일의 수신자 또는 발신자인지 확인
		if (!loginUUID.equals(email.getRecipientUUID()) && !loginUUID.equals(email.getSenderUUID())) {
			// 권한이 없는 사용자가 접근하려는 경우, 접근 거부 페이지로 포워드
			model.addAttribute("errorMessage", "이 이메일을 볼 권한이 없습니다.");
			return "email/error";
		}

		// 이메일 읽음 상태 변경
		if (!"Y".equals(email.getIsRead())) {
			emailService.updateReadStatus(emailId);
		}

		// 이메일을 모델에 추가하여 뷰로 전달
		model.addAttribute("email", email);
		return "email/emailDetail"; // emailDetail.jsp로 포워드
	}

	// 읽음 처리 요청을 처리하는 메서드
	@PostMapping(value = "/email/markAsRead.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> markAsRead(@RequestBody List<Long> emailIds) {
		System.out.println("읽음 처리할 이메일 ID 목록: " + emailIds); // 요청이 제대로 전달되었는지 확인
		emailService.updateReadStatusBatch(emailIds);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
	}

	// 이메일 삭제 요청을 처리하는 메서드
	@PostMapping(value = "/email/deleteEmails.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> deleteEmails(@RequestBody List<Long> emailIds) {
		System.out.println("삭제할 이메일 ID 목록: " + emailIds);
		try {
			emailService.deleteEmailsBatch(emailIds); // EmailService를 통해 삭제 로직 호출
			return ResponseEntity.ok().body("success"); // 성공 시 'success' 문자열 반환
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("fail"); // 실패 시 'fail' 반환
		}
	}

	// 페이징 처리를 한 이메일 전체 페이지
	// page: 현재 페이지를 나타내는 파라미터 (defaultValue = "1")
	/* 페이징처리란 데이터 양이 많을 때 사용자가 한 번에 보면 ~ 하기 때문에,
	 	특정 개수만큼씩 나눠서 볼 수 있도록 분리하는 방식
	 	100 개의 이메일(totalEmails)을 한 페이지 당 10개씩(pageSize) 보여준다면 총 10페이지(totalPages)가 필요하다. */
	@GetMapping("/email/list.do")
	public String emailListPage(@RequestParam(value = "page", defaultValue = "1") int page, HttpSession session,
			Model model) {
		String loginUUID = (String) session.getAttribute("uuid");
		if (loginUUID == null) {
			return "redirect:/";
		}

		// 한 페이지당 표시할 이메일 수
		int pageSize = 10;

		// 페이징을 위한 offset 계산
		/* 현재 페이지의 시작 위치: 
		 * 데이터베이스에서 데이터를 조회할 때, 어느 위치부터 시작할지 결정하는 값이다.
		 * (현재페이지 - 1) * 페이지당 표시할 데이터 수 
		 * 현재 페이지가 3인 경우, offset 은 (3 - 1) * 10 = 20 이다.
		 * 즉, 3번째 페이지는 21번째의 이메일부터 30번째 데이터까지 가져오는 거다. */
		int offset = (page - 1) * pageSize;

		// 총 이메일 수 조회
		int totalEmails = emailService.countEmailsByUser(loginUUID);

		// 총 페이지 수 계산: 나눈 값이 소수점이 발생하면 올림 처리를 해줌 9.5 -> 10
		int totalPages = (int) Math.ceil((double) totalEmails / pageSize);

		// 해당 페이지의 이메일 목록 조회
		List<Email> emails = emailService.selectEmailsByUserWithPaging(loginUUID, offset, pageSize);

		model.addAttribute("loginUUID", loginUUID);
		model.addAttribute("emails", emails);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalEmails", totalEmails);
		model.addAttribute("pageSize", pageSize); // 한 페이지당 표시할 이메일 수  model 에 추가
		model.addAttribute("totalPages", totalPages); // 전체 페이지 수를 model에 추가
		
		return "email/emailList";
	}

}
