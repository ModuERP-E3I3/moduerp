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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.e3i3.moduerp.company.model.dto.Company;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;
import com.e3i3.moduerp.employee.util.ExcelParser;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	//로그인용 메소드
	@RequestMapping("signin.do")
	public String signIn() {
		return "employee/signin";
	}
	
	//직원 등록용 엑셀 파일 업로드 및 처리
	@PostMapping("/registerFromExcel.do")
	public ResponseEntity<String> registerEmployeesFromExcel(@RequestParam("file") MultipartFile file, @ModelAttribute Company company ){
		try {
			// ExcelParser 클래스 사용하여 파일 파싱
			List<Employee> employees = ExcelParser.parseExcelFile(file.getInputStream(), company);
			for(Employee employee: employees) {
				employeeService.insertEmployee(employee);
			}
			return ResponseEntity.ok("직원 정보가 성공적으로 등록되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("직원 등록 중 오류가 발생했습니다.");
		}
		
	}
	
	 // 특정 직원 조회
    @GetMapping("/view.do/{uuid}")
    public String viewEmployee(@PathVariable("uuid") UUID uuid, Model model) {
        Employee employee = employeeService.selectEmployeeByUuid(uuid);
        model.addAttribute("employee", employee);
        return "employee/employeeDetail";
    }
	
    // 모든 직원 목록 조회
    @GetMapping("/list.do")
    public String listAllEmployees(Model model) {
        List<Employee> employees = employeeService.selectAllEmployees();
        model.addAttribute("employees", employees);
        return "employee/employeeList";
    }
    
    // 직원 정보 수정
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
