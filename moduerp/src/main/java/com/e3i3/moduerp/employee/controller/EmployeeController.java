package com.e3i3.moduerp.employee.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	//�α��ο� �޼ҵ�
	@RequestMapping("signin.do")
	public String signIn() {
		return "employee/signin";
	}
	
	
	 // Ư�� ���� ��ȸ
    @GetMapping("/view.do/{uuid}")
    public String viewEmployee(@PathVariable("uuid") UUID uuid, Model model) {
        Employee employee = employeeService.selectEmployeeByUuid(uuid);
        model.addAttribute("employee", employee);
        return "employee/employeeDetail";
    }
	
    // ��� ���� ��� ��ȸ
    @GetMapping("/list.do")
    public String listAllEmployees(Model model) {
        List<Employee> employees = employeeService.selectAllEmployees();
        model.addAttribute("employees", employees);
        return "employee/employeeList";
    }
    
    // ���� ���� ����
    @PutMapping("/edit.do/{uuid}")
    public ResponseEntity<String> updateEmployee(@PathVariable("uuid") UUID uuid, @RequestBody Employee employee) {
        employee.setUuid(uuid);
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok("직원 정보가 성공적으로 수정되었습니다.");
    }
    
    // 직원 정보 삭제
    @DeleteMapping("/delete.do/{uuid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("uuid") UUID uuid) {
        employeeService.deleteEmployee(uuid);
        return ResponseEntity.ok("직원 정보가 성공적으로 삭제되었습니다.");
    }
}
