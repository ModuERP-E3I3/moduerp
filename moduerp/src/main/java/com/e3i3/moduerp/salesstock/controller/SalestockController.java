package com.e3i3.moduerp.salesstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SalestockController {

    // salesStockIn.jsp ��û ó��
    @RequestMapping(value = "/salesStockIn.do", method = RequestMethod.GET)
    public String forwardSalesStockIn() {
        return "salesstock/salesStockIn";  // JSP ���� ��� ��ȯ
    }

    // salesStockOut.jsp ��û ó��
    @RequestMapping(value = "/salesStockOut.do", method = RequestMethod.GET)
    public String forwardSalesStockOut() {
        return "salesstock/salesStockOut";  // JSP ���� ��� ��ȯ
    }
}
