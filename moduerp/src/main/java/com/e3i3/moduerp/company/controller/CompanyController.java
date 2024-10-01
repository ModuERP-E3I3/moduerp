package com.e3i3.moduerp.company.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.e3i3.moduerp.company.model.dto.Company;
import com.e3i3.moduerp.company.model.service.CompanyService;
import com.e3i3.moduerp.department.model.dto.Department;
import com.e3i3.moduerp.department.model.service.DepartmentService;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;
import com.e3i3.moduerp.employee.util.ExcelParser;

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

    // 1. 회원가입 페이지 이동
   @RequestMapping("/signup.do")
   public String signUp() {
      return "company/signup";
   }

   // 2. 회사 등록 및 부서와 직원 정보 등록
   @PostMapping("/register.do")
   public String registerCompany(@RequestParam("bizNumber") String bizNumber,
            @RequestParam("approvalCode") String approvalCode,
            @RequestParam("companyName") String companyName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("fileUpload") MultipartFile file,
            Model model) {
       try {
      // 1) 회사 정보 생성 및 저장
      Company company=new Company()
            .setBizNumber(bizNumber)
            .setApprovalCode(approvalCode)
            .setCompanyName(companyName)
              .setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
      
      
      companyService.insertCompany(company);
      
      
         // 2) 엑셀파일에서 부서 정보 및 직원 정보 파싱하여 저장
         List<Department> departments=ExcelParser.parseDepartments(file.getInputStream(), bizNumber);
         List<Employee> employees = ExcelParser.parseEmployees(file.getInputStream(), company);
         
         // 3) 부서 및 직원 정보 저장
         for(Department department: departments) {
             System.out.println("Department ID: " + department.getDepartmentId() + ", Name: " + department.getDepartmentName());
            departmentService.insertDepartment(department);
         }
          // 암호화된 비밀번호 생성
           String encodedPassword = bcryptPasswordEncoder.encode(password);
         
         // 사장님 정보도 직원 테이블에 저장
         Employee ceo=new Employee()
                   .setUuid(java.util.UUID.randomUUID())
                       .setBizNumber(bizNumber)
                       .setApprovalCode(approvalCode)
                       .setDepartmentId("ceo-dpt")
                       .setPrivateAuthority('N')
                       .setLastLoginLocation("default")
                       .setIsEmailChanged('N')
                       .setEmpEmail(email)
                       .setPassword(encodedPassword) //암호화된 비밀번호 설정
                       .setEmpNo("CEO001")
                       .setEmpName("CEO")
                       .setProfileImg(null)
                       .setRegistrationDate(new java.sql.Date(System.currentTimeMillis()));
         
         employees.add(ceo);
         
         for(Employee employee: employees) {
            // 직원 정보 저장 시 암호 필드 설정
            if(employee.getPassword()==null || employee.getPassword().isEmpty()) {
                 employee.setPassword(encodedPassword); // 기본 암호 설정
            }
            employeeService.insertEmployee(employee);
         }
         
         // 회원가입 성공 시 로그인 페이지로 이동
           return "redirect:/signin.do";  // 절대 경로로 수정
         
       }catch (RuntimeException e) {
              if (e.getMessage().contains("Company with the same bizNumber")) {
                  model.addAttribute("message", "이미 동일한 사업자번호로 등록된 회사가 존재합니다.");
                  return "company/error"; // 에러 페이지로 이동
              }
              e.printStackTrace();
              model.addAttribute("message", "회사 등록 중 오류가 발생했습니다.");
              return "company/error";
      }catch(IOException e) {
         e.printStackTrace();
         model.addAttribute("message", "엑셀 파일 처리 중 오류가 발생했습니다.");
         return "company/error";
      }
   }

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
