package com.e3i3.moduerp.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AccountController {

    // account.jsp ��û ó��
    @RequestMapping(value = "/account.do", method = RequestMethod.GET)
    public String forwardAccount() {
        return "account/account";  // JSP ���� ��� ��ȯ
    }
}
