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
	                           @RequestParam("approvalCode") String approvalCode,
	                           @RequestParam("empEmail") String empEmail,
	                           @RequestParam("password") String password,
	                           Model model, HttpSession session) {

	    Map<String, Object> params = new HashMap<>();
	    params.put("bizNumber", bizNumber);
	    params.put("approvalCode", approvalCode);
	    params.put("empEmail", empEmail);
	    params.put("password", password);

	    Employee employee = employeeService.validateLogin(params);

	    if (employee != null) {
	        // 로그인 성공: 세션에 uuid와 biz_number 저장
	        session.setAttribute("uuid", employee.getUuid());
	        session.setAttribute("biz_number", employee.getBizNumber());
	        
	        // 콘솔에 로그인 세션 정보 출력
	        System.out.println("로그인한 사용자의 UUID: " + session.getAttribute("uuid"));
	        System.out.println("로그인한 사용자의 사업자번호: " + session.getAttribute("biz_number"));
	        
	        
	        System.out.println("쿼리로 반환된 UUID: " + employee.getUuid());
	        System.out.println("쿼리로 반환된 사업자번호: " + employee.getBizNumber());
	        

	        if ("ceo-dpt".equals(employee.getDepartmentId())) {
	            // 사장일 경우 main.jsp로 이동
	            model.addAttribute("message", "사장님 로그인 성공");
	            return "common/main";
	        } else {
	            // 사원일 경우 erpMain.jsp로 이동
	            model.addAttribute("message", "사원 로그인 성공");
	            return "common/erpMain";
	        }
	    } else {
	        // 로그인 실패 처리
	        model.addAttribute("errorMessage", "로그인 실패: 사업자번호, 승인코드, 이메일 또는 비밀번호가 잘못되었습니다.");
	        return "employee/signin";
	    }
	}


	// uuid로 직원 조회
	@GetMapping("/view.do/{uuid}")
	public String viewEmployee(@PathVariable("uuid") UUID uuid, Model model) {
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
	public ResponseEntity<String> updateEmployee(@PathVariable("uuid") UUID uuid, @RequestBody Employee employee) {
		employee.setUuid(uuid);
		employeeService.updateEmployee(employee);
		return ResponseEntity.ok(uuid + "직원 수정 성공했습니다..");
	}

	// uuid로 직원 삭제
	@DeleteMapping("/delete.do/{uuid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("uuid") UUID uuid) {
		employeeService.deleteEmployee(uuid);
		return ResponseEntity.ok(uuid + "직원 삭제 성공했습니다..");
	}
}