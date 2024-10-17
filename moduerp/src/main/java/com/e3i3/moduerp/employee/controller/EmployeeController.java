package com.e3i3.moduerp.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@Value("${admin.uuid}")
	private String adminUUID;

	// 로그인 페이지 반환
	@RequestMapping("signin.do")
	public String signInPage() {
		return "employee/signin";
	}

	@SuppressWarnings("unused")
	// 로그인 처리 메서드 수정
	@PostMapping("/login.do")
	public String signinMethod(@RequestParam("bizNumber") String bizNumber,
			@RequestParam("approvalCode") String approvalCode, @RequestParam("empEmail") String empEmail,
			@RequestParam("password") String password, Model model, HttpSession session) {

		Map<String, Object> params = new HashMap<>();
		params.put("bizNumber", bizNumber);
		params.put("approvalCode", approvalCode);
		params.put("empEmail", empEmail);
		params.put("password", password);

		UUID uuid; // 차량의 고유한 UUID (PK)직원 테이블의 FK가 아닌 ///// CAR 테이블의 PK
		Employee employee = employeeService.validateLogin(params); // 로그인처리하는 서비스 메소드

		if (employee != null) {
			// 로그인 성공: 세션에 uuid와 biz_number, email 저장
//	        session.setAttribute("uuid", employee.getUuid());
			session.setAttribute("uuid", employee.getUuid().toString());
			session.setAttribute("biz_number", employee.getBizNumber());
			
			session.setAttribute("email", employee.getEmpEmail());
			session.setAttribute("name", employee.getEmpName());
			session.setAttribute("departmentId", employee.getDepartmentId());
			session.setAttribute("adminUUID", adminUUID); // 세션에 개발자 uuid 넣어줌
			session.setAttribute("address", employee.getAddress());

			// 콘솔에 로그인 세션 정보 출력
			System.out.println("로그인한 사용자의 UUID: " + session.getAttribute("uuid"));
			System.out.println("로그인한 사용자의 사업자번호: " + session.getAttribute("biz_number"));
			System.out.println("로그인한 사용자의 이메일: " + session.getAttribute("email"));
			System.out.println("로그인한 사용자의 이름: " + session.getAttribute("name"));
			System.out.println("개발자 uuid: " + session.getAttribute("adminUUID"));

			System.out.println("쿼리로 반환된 UUID: " + employee.getUuid());
			System.out.println("쿼리로 반환된 사업자번호: " + employee.getBizNumber());
			System.out.println("쿼리로 반환된 이메일: " + employee.getEmpEmail());

			if ("ceo-dpt".equals(employee.getDepartmentId())) {
				// 사장일 경우 main.jsp로 이동
				model.addAttribute("message", "사장님 로그인 성공");
				return "redirect:/main.do";
			} else {
				// 사원일 경우 "/attendance.do"로 리다이렉트
				model.addAttribute("message", "사원 로그인 성공");
				return "redirect:/attendance.do";
			}
		} else {
			// 로그인 실패 처리
			model.addAttribute("errorMessage", "로그인 실패: 사업자번호, 승인코드, 이메일 또는 비밀번호가 잘못되었습니다.");
			return "employee/signin";
		}
	}

	// 로그아웃 처리 메서드
	@GetMapping("/logout.do")
	public String logoutMethod(HttpSession session, Model model) {
		// 세션 무효화
		if (session != null) {
			// 콘솔에 로그아웃할 세션 정보 출력
			System.out.println("로그아웃하는 사용자의 UUID: " + session.getAttribute("uuid"));
			System.out.println("로그아웃하는 사용자의 사업자번호: " + session.getAttribute("biz_number"));
			System.out.println("로그아웃하는 사용자의 이메일: " + session.getAttribute("email"));
			System.out.println("로그아웃하는 사용자의 이름: " + session.getAttribute("name"));

			// 세션 무효화
			session.invalidate();
		}

		// 로그아웃 후 로그인 페이지로 리다이렉트
		model.addAttribute("message", "로그아웃되었습니다.");
		return "redirect:/"; // 로그인 페이지 URL로 리다이렉트
	}

	/* --------- 비밀번호 관리 ---------- */

	@PostMapping("/updatePassword.do")
	public String updatePassword(@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
			RedirectAttributes redirectAttributes, // 모델을 RedirectAttributes로 변경
			HttpSession session) {

		// 1. 현재 인증된 사용자 정보 가져오기
		String uuid = (String) session.getAttribute("uuid");
		Employee currentCeo = employeeService.selectEmployeeByUuid(uuid);

		if (currentCeo == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "사용자를 찾을 수 없습니다.");
			return "redirect:/passwordManagement.do";
		}

		// 3. 현재 비밀번호 확인
		if (!bcryptPasswordEncoder.matches(currentPassword, currentCeo.getPassword())) {
			redirectAttributes.addFlashAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
			return "redirect:/passwordManagement.do";
		}

		// 4. 새 비밀번호 유효성 검사
		if (newPassword.length() < 8) {
			redirectAttributes.addFlashAttribute("errorMessage", "새 비밀번호는 최소 8자리 이상이어야 합니다.");
			return "redirect:/passwordManagement.do";
		}

		// 5. 새 비밀번호와 확인 비밀번호 일치 여부 확인
		if (!newPassword.equals(confirmPassword)) {
			redirectAttributes.addFlashAttribute("errorMessage", "비밀번호 확인이 일치하지 않습니다.");
			return "redirect:/passwordManagement.do";
		}

		// 6. 새 비밀번호 암호화
		String encodedNewPassword = bcryptPasswordEncoder.encode(newPassword);

		// 7. 비밀번호 업데이트 (Service 또는 Repository를 통해)
		currentCeo.setPassword(encodedNewPassword);
		employeeService.updatePassword(uuid, encodedNewPassword);

		// 8. 성공 메시지 전달
		redirectAttributes.addFlashAttribute("successMessage", "비밀번호가 성공적으로 변경되었습니다.");
		return "redirect:/passwordManagement.do";
	}

	// uuid로 직원 조회
	@GetMapping("/view.do/{uuid}")
	public String viewEmployee(@PathVariable("uuid") String uuid, Model model) {
		Employee employee = employeeService.selectEmployeeByUuid(uuid);
		model.addAttribute("employee", employee);
		return "employee/employeeDetail";
	}

	// 존재하는 모든 직원 조회
	@GetMapping("/list.do")
	public String listAllEmployees(Model model) {
		List<Employee> employees = employeeService.selectAllEmployees();
		model.addAttribute("employees", employees);
		return "employee/employeeList";
	}

	// 직원 수정
	@PutMapping("/edit.do/{uuid}")
	public ResponseEntity<String> updateEmployee(@PathVariable("uuid") String uuid, @RequestBody Employee employee) {
		employee.setUuid(uuid);
		employeeService.updateEmployee(employee);
		return ResponseEntity.ok(uuid + "직원 수정 성공했 습니다..");
	}

	// uuid로 직원 삭제
	@DeleteMapping("/delete.do/{uuid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("uuid") String uuid) {
		employeeService.deleteEmployee(uuid);
		return ResponseEntity.ok(uuid + "직원 삭제 성공했습니다..");
	}
}