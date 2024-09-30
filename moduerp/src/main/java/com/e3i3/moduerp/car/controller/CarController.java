package com.e3i3.moduerp.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class CarController {
	
	// carRes.jsp ��û ó��
    @RequestMapping(value = "/carRes.do", method = RequestMethod.GET)
    public String forwardCarRes() {
        return "car/carRes";  // JSP ���� ��� ��ȯ
    }
    
    // carMgt.jsp
    @RequestMapping(value = "/carMgt.do", method = RequestMethod.GET)
    public String forwardCarMgt() {
        return "car/carMgt";  // JSP ���� ��� ��ȯ
    }
}
