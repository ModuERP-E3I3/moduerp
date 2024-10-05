package com.e3i3.moduerp.financialclosing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class FinancialclosingController {

    // attendance.jsp 요청 처리
    @RequestMapping(value = "/financialClosing.do", method = RequestMethod.GET)
    public String forwardFinancialclosing() {
        return "financialClosing/financialClosing";  // JSP 파일 경로 반환
    }
}
