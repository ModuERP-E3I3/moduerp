package com.e3i3.moduerp.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AttendanceController {

    // attendance.jsp 요청 처리
    @RequestMapping(value = "/attendance.do", method = RequestMethod.GET)
    public String forwardAttendance() {
        return "attendance/attendance";  // JSP 파일 경로 반환
    }
}