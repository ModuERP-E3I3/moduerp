package com.e3i3.moduerp.company.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import org.springframework.web.multipart.MultipartFile;

import com.e3i3.moduerp.billingInfo.model.dto.BillingInfoDTO;
import com.e3i3.moduerp.billingInfo.model.service.BillingInfoService;
import com.e3i3.moduerp.company.model.dto.Company;
import com.e3i3.moduerp.company.model.service.CompanyService;
import com.e3i3.moduerp.department.model.dto.Department;
import com.e3i3.moduerp.department.model.service.DepartmentService;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;
import com.e3i3.moduerp.employee.util.ExcelParser;
import com.e3i3.moduerp.module.model.service.ModuleService;
import com.e3i3.moduerp.paylog.model.dto.PayLogDTO;
import com.e3i3.moduerp.paylog.model.service.PayLogService;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@Autowired
	private BillingInfoService billingInfoService;

	@Autowired
	private PayLogService payLogService;

	@Autowired
	private ModuleService moduleService;

	// 1. 회원가입 페이지 이동
	@RequestMapping("/signup.do")
	public String signUp() {
		return "company/signup";
	}

	@PostMapping("/register.do")
	public String registerCompany(@RequestParam("bizNumber") String bizNumber,
			@RequestParam("companyName") String companyName, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("fileUpload") MultipartFile file, Model model) {
		try {
			// 1) 승인코드 6자리 랜덤 생성
			String approvalCode = String.format("%06d", (int) (Math.random() * 1000000));

			// 2) 회사 정보 생성 및 저장
			Company company = new Company().setBizNumber(bizNumber).setApprovalCode(approvalCode)
					.setCompanyName(companyName).setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

			companyService.insertCompany(company);

			// 3) 엑셀파일에서 부서 정보 및 직원 정보 파싱하여 저장
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			List<Department> departments = ExcelParser.parseDepartments(workbook, bizNumber);
			List<Employee> employees = ExcelParser.parseEmployees(workbook, company, departments);
			workbook.close();

			// 4) 부서 및 직원 정보 저장
			for (Department department : departments) {
				departmentService.insertDepartment(department);
			}

			// 5) 비밀번호 암호화 처리
			String encodedPassword = bcryptPasswordEncoder.encode(password);

			// 6) 사장님 정보도 직원 테이블에 저장
			Employee ceo = new Employee().setUuid(java.util.UUID.randomUUID().toString()).setBizNumber(bizNumber)
					.setApprovalCode(approvalCode).setDepartmentId("ceo-dpt").setIsDeleted('N').setPrivateAuthority('N')
					.setLastLoginLocation("default").setIsEmailChanged('N').setEmpEmail(email)
					.setPassword(encodedPassword).setEmpNo(bizNumber + "_CEO").setEmpName(bizNumber + "_CEO")
					.setProfileImg(null).setRegistrationDate(new java.sql.Date(System.currentTimeMillis()));

			employees.add(ceo);

			for (Employee employee : employees) {
				if (employee.getPassword() == null || employee.getPassword().isEmpty()) {
					employee.setPassword(encodedPassword); // 기본 암호 설정
				}
				if (employee.getDepartmentId() == null || employee.getDepartmentId().isEmpty()) {
					employee.setDepartmentId("UNIQUE_DEPT_ID_" + UUID.randomUUID());
				}

				try {
					employeeService.insertEmployee(employee);
				} catch (DuplicateKeyException e) {
					System.out.println("중복된 departmentId로 인해 삽입 실패: " + employee.getDepartmentId());
				}
			}

			// 7) 승인코드 출력 페이지로 이동 (모델에 승인코드 추가)
			model.addAttribute("approvalCode", approvalCode);
			return "company/approvalCodeView";

		} catch (RuntimeException e) {
			if (e.getMessage().contains("Company with the same bizNumber")) {
				model.addAttribute("message", "이미 동일한 사업자번호로 등록된 회사가 존재합니다.");
				return "company/error";
			}
			e.printStackTrace();
			model.addAttribute("message", "회사 등록 중 오류가 발생했습니다.");
			return "company/error";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("message", "엑셀 파일 처리 중 오류가 발생했습니다.");
			return "company/error";
		}
	}

	/* *** 마이페이지 관련!!! **** */

	// 판매사이트 사장님 마이페이지
	/*
	 * @GetMapping("/mypage.do") public String ShowMyPage(Model model) {
	 * model.addAttribute("activePage", "passwordManagement"); return
	 * "common/mypage"; }
	 */

	@GetMapping("/passwordManagement.do")
	public String passwordManagement(Model model) {
		model.addAttribute("contentPage", "/WEB-INF/views/mypage/passwordManagement.jsp");
		return "common/mypage"; // 공통 레이아웃 JSP 반환
	}

	// Mypage Card
	@GetMapping("/cardManagement.do")
	public String cardManagement(Model model, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");

		// 회사 카드 가져오기
		BillingInfoDTO cardInfo = billingInfoService.selectBillingInfoByBizNumber(bizNumber);
		System.out.println("카드 정보: " + cardInfo);

		

		String moduleGrades = companyService.selectCompanyModuleGradesByBizNumber(bizNumber);
		// moduleGrades가 null이면 빈 문자열로 처리
		if (moduleGrades == null) {
			moduleGrades = "";
		}

		// ,로 문자열을 분리해서 배열에 저장
		String[] gradesArray = moduleGrades.split(",");

		// 배열을 List로 변환
		List<String> gradesList = Arrays.asList(gradesArray);

		List<String> moduleNames = moduleService.selectModuleNamesByGradesList(gradesList);
		// 리스트의 각 요소를 ,로 합쳐서 하나의 문자열로 변환
		String moduleNamesString = String.join(", ", moduleNames);

		model.addAttribute("contentPage", "/WEB-INF/views/mypage/cardManagement.jsp");
		model.addAttribute("cardInfo", cardInfo); // null이 아닌 객체를 넘김
		model.addAttribute("moduleNamesString", moduleNamesString);

		return "common/mypage"; // 공통 레이아웃 JSP 반환
	}

	// 카드 삭제 요청 처리
	@RequestMapping(value = "/deleteCardBilling.do", method = RequestMethod.GET)
	public String deleteCardBilling(@RequestParam("cardBillingId") String cardBillingId, HttpSession session) {
		System.out.println("카드 id : " + cardBillingId);

		String bizNumber = (String) session.getAttribute("biz_number");

		billingInfoService.deleteBillingInfoByCardBillingId(cardBillingId);

		companyService.deleteCardExistenceByBizNumber(bizNumber);

		return "redirect:/cardManagement.do";
	}

	@GetMapping("/cardLog.do")
	public String cardLog(Model model, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");

		List<PayLogDTO> payLog = payLogService.selectPayLogByBizNumber(bizNumber);

		model.addAttribute("payLog", payLog);
		model.addAttribute("contentPage", "/WEB-INF/views/mypage/cardLog.jsp");

		return "common/mypage"; // 공통 레이아웃 JSP 반환
	}

	/* -----여기까지------- */

	// 2. 회사 수정
	@PutMapping("/update.do")
	public ResponseEntity<String> updateCompany(@RequestBody Company company) {
		companyService.updateCompany(company);
		return ResponseEntity.ok("회사 정보가 성공적으로 수정되었습니다.");
	}

	// 3. 회사 삭제
	@DeleteMapping("/delete/{bizNumber}.do")
	public ResponseEntity<String> deleteCompany(@PathVariable String bizNumber) {
		companyService.deleteCompany(bizNumber);
		return ResponseEntity.ok("회사 정보가 성공적으로 삭제되었습니다.");
	}

	// 4. 사업자번호로 특정 회사 조회
	@GetMapping("/{bizNumber}.do")
	public ResponseEntity<Company> getCompanyByBizNumber(@PathVariable String bizNumber) {
		Company company = companyService.selectCompanyByBizNumber(bizNumber);
		return ResponseEntity.ok(company);
	}

	// 5. 사업자번호로 특정 회사 및 관련 부서 조회
	@GetMapping("/{bizNumber}/departments.do")
	public ResponseEntity<Company> getCompanyWithDepartments(@PathVariable String bizNumber) {
		Company company = companyService.selectCompanyWithDepartments(bizNumber);
		return ResponseEntity.ok(company);
	}

	// 6. 모든 회사 조회
	@GetMapping("/all.do")
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> companies = companyService.selectAllCompanies();
		return ResponseEntity.ok(companies);
	}

}