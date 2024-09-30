package com.e3i3.moduerp.buystock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BuystockController {
	// buyStockIn.jsp 요청 처리
    @RequestMapping(value = "/buyStockIn.do", method = RequestMethod.GET)
    public String forwardBuyStockIn() {
        return "buyStock/buyStockIn";  // JSP 파일 경로 반환
    }
}
