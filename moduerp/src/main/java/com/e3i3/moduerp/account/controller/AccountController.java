package com.e3i3.moduerp.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AccountController {

    // account.jsp 요청 처리
    @RequestMapping(value = "/account.do", method = RequestMethod.GET)
    public String forwardAccount() {
        return "salesStock/account";  // JSP 파일 경로 반환
    }
}
