package com.e3i3.moduerp.account.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Controller
@RequestMapping("/")
public class AccountController {

    @Autowired
    private com.e3i3.moduerp.account.service.AccountService accountService;

    @RequestMapping(value = "/account.do", method = RequestMethod.GET)
    public String forwardAccount(Model model) {
        List<AccountDTO> accountList = accountService.getAllAccounts();
        model.addAttribute("accountList", accountList);
        return "account/account";
    }

    @RequestMapping(value = "/accountCreate.do", method = RequestMethod.GET)
    public String showCreateAccountForm(Model model, HttpSession session) {
        String bizNumber = (String) session.getAttribute("biz_number");

        // 사원명과 부서명 조회
        List<String> empNames = accountService.getEmpNamesByBizNumber(bizNumber);
        List<String> departmentIds = accountService.getDepartmentIdsByBizNumber(bizNumber);

        List<Employee> empNameDepart = accountService.getEmpNameDepart(bizNumber);

        // 계정 정보 조회
        List<AccountDTO> accounts = accountService.getAccountsByBizNumber(bizNumber);

        model.addAttribute("empNames", empNames);
        model.addAttribute("departmentIds", departmentIds);
        model.addAttribute("accounts", accounts);
        model.addAttribute("empNameDepart", empNameDepart);

        return "account/accountCreate";
    }

    @PostMapping("/insertAccount.do")
    public String insertAccount(@RequestParam("accountName") String accountName, @RequestParam("businessType") String businessType,
            @RequestParam("bossName") String bossName, @RequestParam("creditLimit") Double creditLimit,
            @RequestParam("businessNumber") String businessNumber, @RequestParam("accountAddress") String accountAddress,
            @RequestParam("accountPhone") String accountPhone, @RequestParam("postalCode") String postalCode,
            @RequestParam("email") String email, @RequestParam("fax") String fax, @RequestParam("paymentDate") String paymentDateStr,
            Model model, HttpSession session) {
        String paymentHistoryCode = businessNumber + "AC" + System.currentTimeMillis();
        LocalDate parsedDate = LocalDate.parse(paymentDateStr);
        LocalTime currentTime = LocalTime.now();
        LocalDateTime combinedDateTime = LocalDateTime.of(parsedDate, currentTime);
        Timestamp paymentDate = Timestamp.valueOf(combinedDateTime);

        AccountDTO accountDto = new AccountDTO();
        accountDto.setAccountName(accountName);
        accountDto.setBusinessType(businessType);
        accountDto.setBossName(bossName);
        accountDto.setCreditLimit(creditLimit);
        accountDto.setBusinessNumber(businessNumber);
        accountDto.setAccountAddress(accountAddress);
        accountDto.setAccountPhone(accountPhone);
        accountDto.setPostalCode(postalCode);
        accountDto.setEmail(email);
        accountDto.setFax(fax);

        accountService.insertAccount(accountDto);

        return "redirect:/account.do";
    }

    @GetMapping("getAccountDetail.do")
    public String getAccountDetail(@RequestParam("businessNumber") String businessNumber, Model model) {
        AccountDTO accountDetail = accountService.getAccountListDetail(businessNumber);

        model.addAttribute("accountDetail", accountDetail);

        return "account/accountDetail";
    }

    @GetMapping("accountDetailUpdate.do")
    public String accountDetailUpdate(@RequestParam("businessNumber") String businessNumber, Model model,
            HttpSession session) {
        AccountDTO accountDetail = accountService.getAccountListDetail(businessNumber);

        String bizNumber = (String) session.getAttribute("biz_number");

        List<Employee> empNameDepart = accountService.getEmpNameDepart(bizNumber);

        // 계정 정보 조회
        List<AccountDTO> accounts = accountService.getAccountsByBizNumber(bizNumber);

        model.addAttribute("accounts", accounts);
        model.addAttribute("empNameDepart", empNameDepart);
        model.addAttribute("accountDetail", accountDetail);

        return "account/accountDetailUpdate";
    }

    @PostMapping("/updateAccount.do")
    public String updateAccount(@RequestParam("accountNo") String accountNo, @RequestParam("accountName") String accountName,
            @RequestParam("businessType") String businessType, @RequestParam("bossName") String bossName,
            @RequestParam("creditLimit") Double creditLimit, @RequestParam("businessNumber") String businessNumber,
            @RequestParam("accountAddress") String accountAddress, @RequestParam("accountPhone") String accountPhone,
            @RequestParam("postalCode") String postalCode, @RequestParam("email") String email,
            @RequestParam("fax") String fax, @RequestParam("paymentDate") String paymentDateStr,
            @RequestParam("paymentHistoryCode") String paymentHistoryCode) {
        LocalDate parsedDate = LocalDate.parse(paymentDateStr);
        LocalTime currentTime = LocalTime.now();
        LocalDateTime combinedDateTime = LocalDateTime.of(parsedDate, currentTime);
        Timestamp paymentDate = Timestamp.valueOf(combinedDateTime);

        AccountDTO accountDto = new AccountDTO();
        accountDto.setAccountNo(accountNo);
        accountDto.setAccountName(accountName);
        accountDto.setBusinessType(businessType);
        accountDto.setBossName(bossName);
        accountDto.setCreditLimit(creditLimit);
        accountDto.setBusinessNumber(businessNumber);
        accountDto.setAccountAddress(accountAddress);
        accountDto.setAccountPhone(accountPhone);
        accountDto.setPostalCode(postalCode);
        accountDto.setEmail(email);
        accountDto.setFax(fax);

        accountService.updateAccount(accountDto);

        return "redirect:/account.do";
    }

    @PostMapping("/deleteAccount.do")
    public String deleteAccount(@RequestParam("businessNumber") String businessNumber, HttpSession session) {
        accountService.deleteAccountByBusinessNumber(businessNumber);

        return "redirect:/account.do";
    }
}
