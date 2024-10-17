package com.e3i3.moduerp.empmgt.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.e3i3.moduerp.department.model.dto.Department;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.empmgt.service.EmpMgtService;

@Controller
@RequestMapping("/")
public class EmpMgtController {

	@Autowired
	private EmpMgtService empMgtService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@RequestMapping(value = "/empMgt.do", method = RequestMethod.GET)
	public String forwardEmpMgt(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// 직원 데이터 조회
		List<Employee> employeeList = empMgtService.getEmployeesByBizNumber(bizNumber);

		// Pagination 처리
		int employeesPerPage = 10;
		int totalEmployees = employeeList.size();
		int totalPages = (int) Math.ceil((double) totalEmployees / employeesPerPage);
		int startIndex = (page - 1) * employeesPerPage;
		int endIndex = Math.min(startIndex + employeesPerPage, totalEmployees);
		List<Employee> paginatedList = employeeList.subList(startIndex, endIndex);

		// 모델에 데이터 추가
		model.addAttribute("employeeList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		// privateAuthority
		model.addAttribute("privateAuthority", session.getAttribute("privateAuthority"));

		return "empMgt/empMgt";
	}

	@RequestMapping(value = "/empMgtFilter.do", method = RequestMethod.GET)
	public String forwardEmpMgtFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText, Model model, HttpSession session) {

		// 세션에서 bizNumber 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");

		// 사업자 번호가 세션에 없을 경우 처리
		if (bizNumber == null || bizNumber.isEmpty()) {
			throw new IllegalStateException("Biz number not found in session.");
		}

		// 직원 목록 리스트 초기화
		List<Employee> employeeList;

		// 필터 옵션 및 텍스트 확인 후 필터링
		if (option != null && !option.isEmpty() && filterText != null && !filterText.isEmpty()) {
			// 로그 출력으로 필터 옵션 및 텍스트 확인
			System.out.println("Filtering with Option: " + option + ", Filter Text: " + filterText);

			// 필터 옵션에 따른 직원 목록 가져오기
			if (option.equals("departmentName")) {
				employeeList = empMgtService.getEmployeesByDepartmentName(bizNumber, filterText);
			} else {
				employeeList = empMgtService.getEmployeesByFilter(bizNumber, option, filterText);
			}

			// 만약 필터 결과가 없다면 기본 직원 목록을 조회하도록 처리
			if (employeeList == null || employeeList.isEmpty()) {
				employeeList = empMgtService.getEmployeesByBizNumber(bizNumber);
			}
		} else {
			// 필터 옵션이 없거나 텍스트가 비어있으면 기본 직원 목록 조회
			employeeList = empMgtService.getEmployeesByBizNumber(bizNumber);
		}

		// 페이지네이션 처리
		int itemsPerPage = 10;
		int totalItems = employeeList.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
		List<Employee> paginatedList = employeeList.subList(startIndex, endIndex);

		// 모델에 필요한 데이터 추가
		model.addAttribute("employeeList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);
		return "empMgt/empMgtFilter";
	}

	@RequestMapping(value = "/employeeCreate.do", method = RequestMethod.GET)
	public String showCreateEmployeeForm(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// 부서 목록 가져오기
		List<Department> departmentList = empMgtService.getAllDepartments();

		// UUID 생성 또는 설정
		String uuid = UUID.randomUUID().toString();

		// 추가 데이터 로드
		List<String> empNames = empMgtService.getEmpNamesByBizNumber(bizNumber);
		List<Employee> empNameDepart = empMgtService.getEmpNameDepart(bizNumber);
		List<Employee> employees = empMgtService.getEmployeesByBizNumber(bizNumber);

		// 모델에 추가
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("empNames", empNames);
		model.addAttribute("employees", employees);
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("uuid", uuid);

		return "empMgt/empMgtCreate"; // 생성 폼 뷰 반환
	}

	@PostMapping("/employeeCreate.do")
	public String employeeCreate(
			@RequestParam("empNo") String empNo, 
			@RequestParam("empName") String empName,
			@RequestParam("departmentId") String departmentId, 
			@RequestParam("jobId") String jobId,
			@RequestParam("empEmail") String email, 
			@RequestParam("userPhone") String phone,
			@RequestParam("address") String address,
			@RequestParam("privateAuthority") String privateAuthority,
			@RequestParam("departmentName") String departmentName,
			RedirectAttributes model, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");

		// 승인 코드를 가져오는 로직
		String approvalCode = empMgtService.getApprovalCodeByBizNumber(bizNumber);

		Employee empMgtDTO = new Employee();
		empMgtDTO.setUuid(UUID.randomUUID().toString()); 	// UUID 자동생성
		empMgtDTO.setEmpNo(empNo);							// 사번
		empMgtDTO.setEmpName(empName);						// 직원명
		empMgtDTO.setDepartmentId(departmentId);			// 부서아이디
		empMgtDTO.setJobId(jobId);							// 직급
		empMgtDTO.setEmpEmail(email);						// 이메일
		empMgtDTO.setIsDeleted('N');						// 삭제여부
		empMgtDTO.setUserPhone(phone);						// 전화번호
		empMgtDTO.setDepartmentName(departmentName);		// 부서이름
		empMgtDTO.setAddress(address);						// 주소
		empMgtDTO.setBizNumber(bizNumber);					// 사업자번호
		empMgtDTO.setPrivateAuthority(privateAuthority); 	// 사설 권한
		empMgtDTO.setApprovalCode(approvalCode); 			// 승인 코드
		empMgtDTO.setPassword(bcryptPasswordEncoder.encode("12345678"));

		model.addFlashAttribute("departmentName", departmentName); 

		empMgtService.createEmployee(empMgtDTO);

		return "redirect:/empMgt.do";
	}

	@GetMapping("getEmployeeDetails.do")
	public String getEmployeeDetail(@RequestParam("uuid") String uuid,
			@RequestParam("departmentName") String departmentName, Model model) {
		Employee employeeDetail = empMgtService.getEmployeeDetailByUUID(uuid);
		model.addAttribute("employeeDetail", employeeDetail);
		model.addAttribute("privateAuthority", employeeDetail.getPrivateAuthority()); // privateAuthority 추가
		
		model.addAttribute("departmentName", departmentName);
		return "empMgt/empMgtDetail";
	}

	@GetMapping("employeeDetailUpdate.do")
	public String employeeDetailUpdate(
			@RequestParam("uuid") String uuid, Model model, HttpSession session) {
		Employee employeeDetail = empMgtService.getEmployeeDetailByUUID(uuid);
		String bizNumber = (String) session.getAttribute("biz_number");

		List<Employee> empNameDepart = empMgtService.getEmpNameDepart(bizNumber);
		List<Employee> employees = empMgtService.getEmployeesByBizNumber(bizNumber);

		// 부서 리스트 가져오기
		List<Department> departmentList = empMgtService.getAllDepartments();

		model.addAttribute("employees", employees);
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("employeeDetail", employeeDetail);
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("privateAuthority", employeeDetail.getPrivateAuthority()); // privateAuthority 추가

		return "empMgt/empMgtDetailUpdate";
	}

	@PostMapping("/updateEmployee.do")
	public String updateEmployee(
			@RequestParam("uuid") String uuid, 
			@RequestParam("empName") String empName,
			@RequestParam("empNo") String empNo,
			@RequestParam("departmentId") String departmentId, 
			@RequestParam("jobId") String jobId,
			@RequestParam("empEmail") String email, 
			@RequestParam("userPhone") String phone,
			@RequestParam("address") String address, 
			@RequestParam("privateAuthority") String privateAuthority) { 
																													// 추가

		Employee empMgtDTO = new Employee();
		empMgtDTO.setUuid(uuid);
		empMgtDTO.setEmpName(empName);
		empMgtDTO.setEmpNo(empNo);
		empMgtDTO.setDepartmentId(departmentId);
		empMgtDTO.setJobId(jobId);
		empMgtDTO.setEmpEmail(email);
		empMgtDTO.setUserPhone(phone);
		empMgtDTO.setAddress(address);
		empMgtDTO.setPrivateAuthority(privateAuthority);

		empMgtService.updateEmployee(empMgtDTO);

		return "redirect:/empMgt.do";
	}

	@PostMapping("/deleteEmployee.do")
	public String deleteEmployee(@RequestParam("uuid") String uuid, HttpSession session) {
		empMgtService.deleteEmployeeByUUID(uuid);
		return "redirect:/empMgt.do";
	}
}
