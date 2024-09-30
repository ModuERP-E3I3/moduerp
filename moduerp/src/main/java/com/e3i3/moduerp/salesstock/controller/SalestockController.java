package com.e3i3.moduerp.salesstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/moduerp")
public class SalestockController {

    // salesStockIn.jsp 요청 처리
    @RequestMapping(value = "/salesStockIn.do", method = RequestMethod.GET)
    public String forwardSalesStockIn() {
        return "salesstock/salesStockIn";  // JSP 파일 경로 반환
    }

    // salesStockOut.jsp 요청 처리
    @RequestMapping(value = "/salesStockOut.do", method = RequestMethod.GET)
    public String forwardSalesStockOut() {
        return "salesstock/salesStockOut";  // JSP 파일 경로 반환
    }
}
