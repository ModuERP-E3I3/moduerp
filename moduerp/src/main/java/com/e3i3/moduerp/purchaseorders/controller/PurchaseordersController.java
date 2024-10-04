package com.e3i3.moduerp.purchaseorders.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseordersDTO;
import com.e3i3.moduerp.purchaseorders.service.PurchaseordersService;

@Controller
@RequestMapping("/")
public class PurchaseordersController {

    @Autowired
    private PurchaseordersService purchaseordersService;

    // purchaseorders.jsp 요청 처리
    @RequestMapping("/purchaseorders.do")
    public String showPurchaseorders(Model model) {
        List<PurchaseordersDTO> purchaseordersList = purchaseordersService.getAllPurchaseorders();
        model.addAttribute("purchaseordersList", purchaseordersList);
        return "purchaseorders/purchaseorders";
    }

}
