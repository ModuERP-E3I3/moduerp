package com.e3i3.moduerp.bankaccountmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BankaccountmanagementController {

    // attendance.jsp ��û ó��
    @RequestMapping(value = "/bankmg.do", method = RequestMethod.GET)
    public String forwardBankaccountmanagement() {
        return "bankmg/bankmg";  // JSP ���� ��� ��ȯ
    }
}