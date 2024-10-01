package com.e3i3.moduerp.salesstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.salesstock.model.dto.SalesStockInDTO;
import com.e3i3.moduerp.salesstock.service.SalesStockInService;

@Controller
@RequestMapping("/")
public class SalesStockController {

    @Autowired
    private SalesStockInService salesStockInService;

    // salesStockIn.jsp ��û ó��
    @RequestMapping(value = "/salesStockIn.do", method = RequestMethod.GET)
    public String forwardSalesStockIn(Model model) {
        List<SalesStockInDTO> stockList = salesStockInService.getAllSalesStockIn();
        model.addAttribute("stockList", stockList);
        return "salesStock/salesStockIn";  // JSP ���� ��� ��ȯ
    }

    // salesStockOut.jsp ��û ó��
    @RequestMapping(value = "/salesStockOut.do", method = RequestMethod.GET)
    public String forwardSalesStockOut() {
        return "salesStock/salesStockOut";  // JSP ���� ��� ��ȯ
    }
}

