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

	// �α��� ������ ��ȯ
	@RequestMapping("signin.do")
	public String signInPage() {
		return "employee/signin";
	}

	@SuppressWarnings("unused")
	// �α��� ó�� �޼��� ����
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
	        // �α��� ����: ���ǿ� uuid�� biz_number ����
	        session.setAttribute("uuid", employee.getUuid());
	        session.setAttribute("biz_number", employee.getBizNumber());
	        
	        // �ֿܼ� �α��� ���� ���� ���
	        System.out.println("�α����� ������� UUID: " + session.getAttribute("uuid"));
	        System.out.println("�α����� ������� ����ڹ�ȣ: " + session.getAttribute("biz_number"));
	        
	        
	        System.out.println("������ ��ȯ�� UUID: " + employee.getUuid());
	        System.out.println("������ ��ȯ�� ����ڹ�ȣ: " + employee.getBizNumber());
	        

	        if ("ceo-dpt".equals(employee.getDepartmentId())) {
	            // ������ ��� main.jsp�� �̵�
	            model.addAttribute("message", "����� �α��� ����");
	            return "common/main";
	        } else {
	            // ����� ��� erpMain.jsp�� �̵�
	            model.addAttribute("message", "��� �α��� ����");
	            return "common/erpMain";
	        }
	    } else {
	        // �α��� ���� ó��
	        model.addAttribute("errorMessage", "�α��� ����: ����ڹ�ȣ, �����ڵ�, �̸��� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.");
	        return "employee/signin";
	    }
	}


	// uuid�� ���� ��ȸ
	@GetMapping("/view.do/{uuid}")
	public String viewEmployee(@PathVariable("uuid") UUID uuid, Model model) {
		Employee employee = employeeService.selectEmployeeByUuid(uuid);
		model.addAttribute("employee", employee);
		return "employee/employeeDetail";
	}

	// �����ϴ� ��� ���� ��ȸ
	@GetMapping("/list.do")
	public String listAllEmployees(Model model) {
		List<Employee> employees = employeeService.selectAllEmployees();
		model.addAttribute("employees", employees);
		return "employee/employeeList";
	}

	// ���� ����
	@PutMapping("/edit.do/{uuid}")
	public ResponseEntity<String> updateEmployee(@PathVariable("uuid") UUID uuid, @RequestBody Employee employee) {
		employee.setUuid(uuid);
		employeeService.updateEmployee(employee);
		return ResponseEntity.ok(uuid + "���� ���� �����߽��ϴ�..");
	}

	// uuid�� ���� ����
	@DeleteMapping("/delete.do/{uuid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("uuid") UUID uuid) {
		employeeService.deleteEmployee(uuid);
		return ResponseEntity.ok(uuid + "���� ���� �����߽��ϴ�..");
	}
}