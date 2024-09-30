package com.e3i3.moduerp.productionstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ProductionstockController {

    // productionStockIn.jsp 요청 처리
    @RequestMapping(value = "/productionStockIn.do", method = RequestMethod.GET)
    public String forwardSalesStockIn() {
        return "productionStock/productionStockIn";  // JSP 파일 경로 반환
    }

    // productionStockOut.jsp 요청 처리
    @RequestMapping(value = "/productionStockOut.do", method = RequestMethod.GET)
    public String forwardSalesStockOut() {
        return "productionStock/productionStockOut";  // JSP 파일 경로 반환
    }
}