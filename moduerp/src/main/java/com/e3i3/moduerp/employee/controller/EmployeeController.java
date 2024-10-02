package com.e3i3.moduerp.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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

	// ·Î±×ÀÎ ÆäÀÌÁö ¹ÝÈ¯
	@RequestMapping("signin.do")
	public String signInPage() {
		return "employee/signin";
	}

	@SuppressWarnings("unused")
	// ·Î±×ÀÎ Ã³¸® ¸Þ¼­µå ¼öÁ¤
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

<<<<<<< HEAD
	    Employee employee = employeeService.selectEmployeeForLogin(params);
	    try {
	    	System.out.println("ë¡œê·¸ì¸í•œ ìœ ì €ì˜ ë¶€ì„œì•„ì´ë””: "+employee.getDepartmentId());
	    }catch(NullPointerException e) {
	    	e.getMessage();
	    }
	    
	    // ë¡œê·¸ì¸ ê²°ê³¼ í™•ì¸
	    if (employee != null) {
	    	// ì„¸ì…˜ì— UUIDì™€ bizNumber ì €ìž¥
	    	System.out.println("ì„¸ì…˜ì— ì €ìž¥í•  uuid: "+ employee.getUuid());
	    	System.out.println("ì„¸ì…˜ì— ì €ìž¥í•  bizNum: "+ employee.getBizNumber());
	    	
	    	session.setAttribute("uuid", employee.getUuid()); //ë¡œê·¸ì¸ ì„±ê³µí•œ ì§ì›ì˜ uuid 
	    	session.setAttribute("bizNum", employee.getBizNumber()); // ë¡œê·¸ì¸í•œ ì§ì›ì˜ bizNumber
	    	
	    	// ì‚¬ìž¥ì¸ì§€ ì§ì›ì¸ì§€ì— ë”°ë¥¸ íŽ˜ì´ì§€ ì´ë™
	        if ("ceo-dpt".equals(employee.getDepartmentId())) {
	            // ì‚¬ìž¥ì¼ ê²½ìš° main.jspë¡œ ì´ë™
	        	System.out.println("ì‚¬ìž¥ë‹˜ ë¡œê·¸ì¸ ì„±ê³µ");
	            model.addAttribute("message", "ì‚¬ìž¥ë‹˜ ë¡œê·¸ì¸ ì„±ê³µ");
	            return "redirect:main.do"; // ì„±ê³µ ì‹œ
	        } else {
	            // ì‚¬ì›ì¼ ê²½ìš° erpMain.jspë¡œ ì´ë™
	        	System.out.println("ì‚¬ì› ë¡œê·¸ì¸ ì„±ê³µ");
	            model.addAttribute("message", "ì‚¬ì› ë¡œê·¸ì¸ ì„±ê³µ");
	            return "redirect:erpMain.do"; // ì„±ê³µ ì‹œ
=======
	    Employee employee = employeeService.validateLogin(params);

	    if (employee != null) {
	        // ·Î±×ÀÎ ¼º°ø: ¼¼¼Ç¿¡ uuid¿Í biz_number ÀúÀå
	        session.setAttribute("uuid", employee.getUuid());
	        session.setAttribute("biz_number", employee.getBizNumber());
	        
	        // ÄÜ¼Ö¿¡ ·Î±×ÀÎ ¼¼¼Ç Á¤º¸ Ãâ·Â
	        System.out.println("·Î±×ÀÎÇÑ »ç¿ëÀÚÀÇ UUID: " + session.getAttribute("uuid"));
	        System.out.println("·Î±×ÀÎÇÑ »ç¿ëÀÚÀÇ »ç¾÷ÀÚ¹øÈ£: " + session.getAttribute("biz_number"));
	        
	        
	        System.out.println("Äõ¸®·Î ¹ÝÈ¯µÈ UUID: " + employee.getUuid());
	        System.out.println("Äõ¸®·Î ¹ÝÈ¯µÈ »ç¾÷ÀÚ¹øÈ£: " + employee.getBizNumber());
	        

	        if ("ceo-dpt".equals(employee.getDepartmentId())) {
	            // »çÀåÀÏ °æ¿ì main.jsp·Î ÀÌµ¿
	            model.addAttribute("message", "»çÀå´Ô ·Î±×ÀÎ ¼º°ø");
	            return "common/main";
	        } else {
	            // »ç¿øÀÏ °æ¿ì erpMain.jsp·Î ÀÌµ¿
	            model.addAttribute("message", "»ç¿ø ·Î±×ÀÎ ¼º°ø");
	            return "common/erpMain";
>>>>>>> 5c2e71e708b156708c70447e4b84b5847ef3efe2
	        }
	    } else {
	        // ·Î±×ÀÎ ½ÇÆÐ Ã³¸®
	        model.addAttribute("errorMessage", "·Î±×ÀÎ ½ÇÆÐ: »ç¾÷ÀÚ¹øÈ£, ½ÂÀÎÄÚµå, ÀÌ¸ÞÀÏ ¶Ç´Â ºñ¹Ð¹øÈ£°¡ Àß¸øµÇ¾ú½À´Ï´Ù.");
	        return "employee/signin";
	    }
	}


	// uuid·Î Á÷¿ø Á¶È¸
	@GetMapping("/view.do/{uuid}")
	public String viewEmployee(@PathVariable("uuid") UUID uuid, Model model) {
		Employee employee = employeeService.selectEmployeeByUuid(uuid);
		model.addAttribute("employee", employee);
		return "employee/employeeDetail";
	}

	// Á¸ÀçÇÏ´Â ¸ðµç Á÷¿ø Á¶È¸
	@GetMapping("/list.do")
	public String listAllEmployees(Model model) {
		List<Employee> employees = employeeService.selectAllEmployees();
		model.addAttribute("employees", employees);
		return "employee/employeeList";
	}

	// Á÷¿ø ¼öÁ¤
	@PutMapping("/edit.do/{uuid}")
	public ResponseEntity<String> updateEmployee(@PathVariable("uuid") UUID uuid, @RequestBody Employee employee) {
		employee.setUuid(uuid);
		employeeService.updateEmployee(employee);
		return ResponseEntity.ok(uuid + "Á÷¿ø ¼öÁ¤ ¼º°øÇß½À´Ï´Ù..");
	}

	// uuid·Î Á÷¿ø »èÁ¦
	@DeleteMapping("/delete.do/{uuid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("uuid") UUID uuid) {
		employeeService.deleteEmployee(uuid);
		return ResponseEntity.ok(uuid + "Á÷¿ø »èÁ¦ ¼º°øÇß½À´Ï´Ù..");
	}
}