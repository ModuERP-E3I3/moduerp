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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.e3i3.moduerp.email.model.dto.Email;
import com.e3i3.moduerp.email.model.service.EmailService;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.dto.EmployeeBasicInfo;
import com.e3i3.moduerp.employee.model.service.EmployeeService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@PropertySource("classpath:application.properties") // 프로퍼티 파일 명시적 로드
@Controller
public class EmailController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmployeeService employeeService; // EmployeeService 추가하여 UUID 조회

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

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
	public ResponseEntity<List<EmployeeBasicInfo>> searchEmailsByBizNumber(@RequestParam("keyword") String keyword, HttpSession session) {
	    System.out.println("Inside searchEmailsByBizNumber method...");
	    
	    String bizNumber = (String) session.getAttribute("biz_number");
	    System.out.println("Received Keyword: " + keyword);
	    System.out.println("Current User's BizNumber: " + bizNumber);

	    // 필요한 필드만 사용하여 새로운 DTO 리스트로 변환
	    List<EmployeeBasicInfo> result = employeeService
	            .selectEmployeesByEmailAndBizNumber(keyword, bizNumber)
	            .stream()
	            .map(emp -> new EmployeeBasicInfo(emp.getEmpName(), emp.getEmpEmail())) // 필요한 정보만 DTO로 변환
	            .collect(Collectors.toList());
	    
	    System.out.println("Found Employees: " + result);
	    
	    return ResponseEntity.ok()
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(result);
	}



	// 이메일 전송 처리
	@PostMapping("/email/sending.do")
	public String sendEmail(@ModelAttribute Email email, @RequestParam("file") MultipartFile file, HttpSession session,
			HttpServletRequest request, Model model) throws IOException {
		// 1. 로그인 유저의 이메일 주소와 UUID, 사업자번호 가져오기
		String senderUUID = (String) session.getAttribute("uuid");
		String senderEmail = (String) session.getAttribute("email");
		String bizNumber = (String) session.getAttribute("biz_number"); // 사업자번호 가져오기

		email.setSenderUUID(senderUUID);
		email.setSenderEmail(senderEmail);

		// 2. 수신자의 이메일로 UUID 조회 (EmployeeService를 사용하여 조회)
		String recipientEmail = email.getRecipientEmail();
		Employee recipient = employeeService.selectEmployeeByEmailAndBizNumber(recipientEmail, bizNumber);

		if (recipient == null) {
			model.addAttribute("message", "수신자의 이메일을 찾을 수 없습니다. 같은 회사의 이메일만 입력해 주세요.");
			return "email/sendEmail"; // 수신자 이메일을 찾지 못하면 다시 전송 페이지로 이동
		}

		// 3. 수신자의 UUID 설정
		email.setRecipientUUID(recipient.getUuid().toString());
		email.setRecipientName(recipient.getEmpName()); // 수신자 이름도 설정

		// 4. 현재 시간 설정
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		Timestamp sentDate = Timestamp.valueOf(now);
		// 분 단위의 등록시간을 sendDate로 설정하기
		email.setSentDate(sentDate);

		// 5. 파일 첨부 처리 로직 (생략 가능)
		if (!file.isEmpty()) {
			try {
				// 원본 파일명 가져오기
				String originalFilename = file.getOriginalFilename();

				if (originalFilename == null) {
					model.addAttribute("message", "파일명 정보를 가져올 수 없습니다.");
					return "email/sendEmail";
				}

				// 파일 확장자 검증 (보안 강화)
				List<String> allowedExtensions = Arrays.asList(".jpg", ".png", ".pdf", ".docx");
				int dotIndex = originalFilename.lastIndexOf(".");
				if (dotIndex == -1) {
					model.addAttribute("message", "파일 확장자가 없습니다.");
					return "email/sendEmail";
				}

				String fileExtension = originalFilename.substring(dotIndex).toLowerCase();
				if (!allowedExtensions.contains(fileExtension)) {
					model.addAttribute("message", "허용되지 않은 파일 형식입니다.");
					return "email/sendEmail";
				}

				// 파일명에 ".." 포함 여부 확인 (디렉토리 트래버설 방지)
				if (originalFilename.contains("..")) {
					model.addAttribute("message", "유효하지 않은 파일명입니다.");
					return "email/sendEmail";
				}

				// 새로운 파일명 생성 (첨부한 사람의 이메일 + "_" + 파일명)
				String newFilename = senderEmail + "_" + originalFilename;

				// 파일 저장 경로를 절대 경로로 설정
				// String uploadDir =
				// "D:/erp_workspace/moduerp/src/main/webapp/resources/templates/email_files";
				// 하드코딩 비추천! application.properties 에 경로 지정하기
				String uploadDir = this.uploadDir;

				// 로그 추가 (디버깅용)
				System.out.println("Upload Directory: " + uploadDir);

				// 디렉토리가 존재하지 않으면 생성/
				File dir = new File(uploadDir);
				if (!dir.exists()) {
					boolean dirsCreated = dir.mkdirs();
					if (!dirsCreated) {
						model.addAttribute("message", "파일 저장 디렉토리를 생성할 수 없습니다.");
						return "email/sendEmail";
					}
				}
				// 파일 저장
				File serverFile = new File(dir, newFilename);
				file.transferTo(serverFile);

				// attachmentPath 설정 (파일명만 저장)
				email.setAttachmentPath(newFilename);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
				return "email/sendEmail"; // 오류 발생 시 이메일 전송 페이지로 돌아감
			}
		} else {
			email.setAttachmentPath(null); // 파일 첨부하지 않은 경우
		}

		// 이메일 저장
		emailService.insertEmail(email);
		model.addAttribute("message", "이메일이 전송되었습니다.");

		return "redirect:/email/sent.do"; // 전송 완료 페이지로 리다이렉트
	}

	// 받은 이메일 목록 조회
	@GetMapping("/email/inbox.do")
	public String inbox(Model model, HttpSession session) {
		String recipientEmail = (String) session.getAttribute("email");
		List<Email> emails = emailService.selectEmailsByRecipient(recipientEmail);
		model.addAttribute("emails", emails);

		return "email/inbox"; // 받은 편지함 페이지 뷰 이름
	}

	// 보낸 이메일 목록 조회
	@GetMapping("/email/sent.do")
	public String sent(Model model, HttpSession session) {
		String senderEmail = (String) session.getAttribute("email");
		List<Email> emails = emailService.selectEmailsBySender(senderEmail);
		model.addAttribute("emails", emails);

		return "email/sent"; // 보낸 편지함 페이지 뷰 이름
	}

	/**
	 * 이메일 상세 보기 URL: /email/view.do?emailId=XXX
	 */
	@GetMapping("/email/view.do") // GET 메소드로 명시적으로 지정
	public String viewEmail(@RequestParam("emailId") Long emailId, Model model, HttpSession session) {
		// 세션에서 로그인한 사용자의 이메일 주소 가져오기
		String loggedInEmail = (String) session.getAttribute("email");

		if (loggedInEmail == null) {
			// 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
			return "redirect:/login.do"; // 로그인 페이지 URL로 변경 필요
		}

		// 이메일 조회
		Email email = emailService.selectEmailById(emailId);

		if (email == null) {
			// 이메일이 존재하지 않는 경우, 오류 페이지로 포워드
			model.addAttribute("errorMessage", "해당 이메일을 찾을 수 없습니다.");
			return "error"; // error.jsp로 포워딩 (필요 시 생성)
		}

		// 현재 사용자가 이메일의 수신자 또는 발신자인지 확인
		if (!loggedInEmail.equals(email.getRecipientEmail()) && !loggedInEmail.equals(email.getSenderEmail())) {
			// 권한이 없는 사용자가 접근하려는 경우, 접근 거부 페이지로 포워드
			model.addAttribute("errorMessage", "이 이메일을 볼 권한이 없습니다.");
			return "accessDenied"; // accessDenied.jsp로 포워딩 (필요 시 생성)
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
	@PostMapping("/email/markAsRead")
	@ResponseBody
	public String markAsRead(@RequestBody List<Long> emailIds) {
		System.out.println("읽음 처리할 이메일 ID 목록: " + emailIds); // 요청이 제대로 전달되었는지 확인
		emailService.updateReadStatusBatch(emailIds);
		return "success";
	}

	// 이메일 전체 페이지
	@GetMapping("/email/list.do")
	public String emailListPage(HttpSession session, Model model) {
		String userEmail = (String) session.getAttribute("email"); // 세션에서 이메일 가져오기

		List<Email> emails = emailService.selectEmailsByUser(userEmail); // 로그인 유저의 이메일로 받은/보낸 이메일 조회
		model.addAttribute("emails", emails);
		return "email/emailList"; // 이메일 목록을 보여주는 JSP 페이지 이름
	}
}
