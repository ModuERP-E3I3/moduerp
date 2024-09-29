package com.e3i3.moduerp.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.e3i3.moduerp.company.model.dto.Company;
import com.e3i3.moduerp.company.model.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // 1. 회사 등록
    @PostMapping("/register.do")
    public ResponseEntity<String> registerCompany(@RequestBody Company company) {
        companyService.insertCompany(company);
        return ResponseEntity.ok("회사 등록이 성공적으로 완료되었습니다.");
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

    // 7. 회사 등록 페이지 이동 (JSP 페이지와의 연동)
    @GetMapping("/registerPage.do")
    public String showCompanyRegistrationForm(Model model) {
        model.addAttribute("company", new Company());
        return "company/registerForm";  // company/registerForm.jsp로 이동
    }
}
