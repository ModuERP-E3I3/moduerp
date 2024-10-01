package com.e3i3.moduerp.leave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LeaveController {

    // leave.jsp 요청 처리
    @RequestMapping(value = "/leave.do", method = RequestMethod.GET)
    public String forwardLeave() {
        return "leave/leave";  // JSP 파일 경로 반환
    }
}