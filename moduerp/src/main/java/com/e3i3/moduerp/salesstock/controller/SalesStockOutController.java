package com.e3i3.moduerp.salesstock.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockOutDTO;
import com.e3i3.moduerp.salesstock.service.SalesStockOutService;

@Controller
@RequestMapping("/")
public class SalesStockOutController {

    @Autowired
    private SalesStockOutService salesStockOutService;

    // salesStockOut.jsp 요청 처리
    @RequestMapping("/salesStockOut.do")
    public String showSalesStockOut(Model model) {
        List<SalesStockOutDTO> stockOutList = salesStockOutService.getAllSalesStockOuts();
        model.addAttribute("stockOutList", stockOutList);
        return "salesStock/salesStockOut"; // salesStockOut.jsp
    }

}
