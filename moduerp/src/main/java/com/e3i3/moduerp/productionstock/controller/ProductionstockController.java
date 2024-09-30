package com.e3i3.moduerp.productionstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ProductionstockController {

    // productionStockIn.jsp ��û ó��
    @RequestMapping(value = "/productionStockIn.do", method = RequestMethod.GET)
    public String forwardProductionIn() {
        return "productionStock/productionStockIn";  // JSP ���� ��� ��ȯ
    }

    // productionStockOut.jsp ��û ó��
    @RequestMapping(value = "/productionStockOut.do", method = RequestMethod.GET)
    public String forwardProductionOut() {
        return "productionStock/productionStockOut";  // JSP ���� ��� ��ȯ
    }
    
 // productionWorkorder.jsp ��û ó��
    @RequestMapping(value = "/productionWorkorder.do", method = RequestMethod.GET)
    public String forwardProductionWorkorder() {
        return "productionStock/productionWorkorder";  // JSP ���� ��� ��ȯ
    }

    // productionQuality.jsp ��û ó��
    @RequestMapping(value = "/productionQuality.do", method = RequestMethod.GET)
    public String forwardProductionQuality() {
        return "productionStock/productionQuality";  // JSP ���� ��� ��ȯ
    }
}