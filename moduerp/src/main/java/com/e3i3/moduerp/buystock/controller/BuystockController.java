package com.e3i3.moduerp.buystock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BuystockController {
	// productionStockIn.jsp ��û ó��
    @RequestMapping(value = "/buyStockIn.do", method = RequestMethod.GET)
    public String forwardProductionIn() {
        return "buyStock/buyStockIn";  // JSP ���� ��� ��ȯ
    }
}
