package com.e3i3.moduerp.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

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

			// 콘솔에 로그인 세션 정보 출력
			System.out.println("로그인한 사용자의 UUID: " + session.getAttribute("uuid"));
			System.out.println("로그인한 사용자의 사업자번호: " + session.getAttribute("biz_number"));
			System.out.println("로그인한 사용자의 이메일: " + session.getAttribute("email"));
			System.out.println("로그인한 사용자의 이름: " + session.getAttribute("name"));

			System.out.println("쿼리로 반환된 UUID: " + employee.getUuid());
			System.out.println("쿼리로 반환된 사업자번호: " + employee.getBizNumber());
			System.out.println("쿼리로 반환된 이메일: " + employee.getEmpEmail());

			if ("ceo-dpt".equals(employee.getDepartmentId())) {
				// 사장일 경우 main.jsp로 이동
				model.addAttribute("message", "사장님 로그인 성공");
				return "common/main";
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