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

    // 1. ȸ������ ������ �̵�
   @RequestMapping("/signup.do")
   public String signUp() {
      return "company/signup";
   }

   // 2. ȸ�� ��� �� �μ��� ���� ���� ���
   @PostMapping("/register.do")
   public String registerCompany(@RequestParam("bizNumber") String bizNumber,
            @RequestParam("approvalCode") String approvalCode,
            @RequestParam("companyName") String companyName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("fileUpload") MultipartFile file,
            Model model) {
       try {
      // 1) ȸ�� ���� ���� �� ����
      Company company=new Company()
            .setBizNumber(bizNumber)
            .setApprovalCode(approvalCode)
            .setCompanyName(companyName)
              .setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
      
      
      companyService.insertCompany(company);
      
      
         // 2) �������Ͽ��� �μ� ���� �� ���� ���� �Ľ��Ͽ� ����
         List<Department> departments=ExcelParser.parseDepartments(file.getInputStream(), bizNumber);
         List<Employee> employees = ExcelParser.parseEmployees(file.getInputStream(), company);
         
         // 3) �μ� �� ���� ���� ����
         for(Department department: departments) {
             System.out.println("Department ID: " + department.getDepartmentId() + ", Name: " + department.getDepartmentName());
            departmentService.insertDepartment(department);
         }
          // ��ȣȭ�� ��й�ȣ ����
           String encodedPassword = bcryptPasswordEncoder.encode(password);
         
         // ����� ������ ���� ���̺��� ����
         Employee ceo=new Employee()
                   .setUuid(java.util.UUID.randomUUID())
                       .setBizNumber(bizNumber)
                       .setApprovalCode(approvalCode)
                       .setDepartmentId("ceo-dpt")
                       .setPrivateAuthority('N')
                       .setLastLoginLocation("default")
                       .setIsEmailChanged('N')
                       .setEmpEmail(email)
                       .setPassword(encodedPassword) //��ȣȭ�� ��й�ȣ ����
                       .setEmpNo("CEO001")
                       .setEmpName("CEO")
                       .setProfileImg(null)
                       .setRegistrationDate(new java.sql.Date(System.currentTimeMillis()));
         
         employees.add(ceo);
         
         for(Employee employee: employees) {
            // ���� ���� ���� �� ��ȣ �ʵ� ����
            if(employee.getPassword()==null || employee.getPassword().isEmpty()) {
                 employee.setPassword(encodedPassword); // �⺻ ��ȣ ����
            }
            employeeService.insertEmployee(employee);
         }
         
         // ȸ������ ���� �� �α��� �������� �̵�
           return "redirect:/signin.do";  // ���� ��η� ����
         
       }catch (RuntimeException e) {
              if (e.getMessage().contains("Company with the same bizNumber")) {
                  model.addAttribute("message", "�̹� ������ ����ڹ�ȣ�� ��ϵ� ȸ�簡 �����մϴ�.");
                  return "company/error"; // ���� �������� �̵�
              }
              e.printStackTrace();
              model.addAttribute("message", "ȸ�� ��� �� ������ �߻��߽��ϴ�.");
              return "company/error";
      }catch(IOException e) {
         e.printStackTrace();
         model.addAttribute("message", "���� ���� ó�� �� ������ �߻��߽��ϴ�.");
         return "company/error";
      }
   }

   // 2. ȸ�� ����
   @PutMapping("/update.do")
   public ResponseEntity<String> updateCompany(@RequestBody Company company) {
      companyService.updateCompany(company);
      return ResponseEntity.ok("ȸ�� ������ ���������� �����Ǿ����ϴ�.");
   }

   // 3. ȸ�� ����
   @DeleteMapping("/delete/{bizNumber}.do")
   public ResponseEntity<String> deleteCompany(@PathVariable String bizNumber) {
      companyService.deleteCompany(bizNumber);
      return ResponseEntity.ok("ȸ�� ������ ���������� �����Ǿ����ϴ�.");
   }

   // 4. ����ڹ�ȣ�� Ư�� ȸ�� ��ȸ
   @GetMapping("/{bizNumber}.do")
   public ResponseEntity<Company> getCompanyByBizNumber(@PathVariable String bizNumber) {
      Company company = companyService.selectCompanyByBizNumber(bizNumber);
      return ResponseEntity.ok(company);
   }

   // 5. ����ڹ�ȣ�� Ư�� ȸ�� �� ���� �μ� ��ȸ
   @GetMapping("/{bizNumber}/departments.do")
   public ResponseEntity<Company> getCompanyWithDepartments(@PathVariable String bizNumber) {
      Company company = companyService.selectCompanyWithDepartments(bizNumber);
      return ResponseEntity.ok(company);
   }

   // 6. ��� ȸ�� ��ȸ
   @GetMapping("/all.do")
   public ResponseEntity<List<Company>> getAllCompanies() {
      List<Company> companies = companyService.selectAllCompanies();
      return ResponseEntity.ok(companies);
   }

}