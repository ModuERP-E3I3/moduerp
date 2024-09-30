package com.e3i3.moduerp.purchaseorders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PurchaseordersController {

    // purchaseOrder.jsp 요청 처리
    @RequestMapping(value = "/purchaseOrder.do", method = RequestMethod.GET)
    public String forwardPurchaseOrder() {
        return "purchaseorders/purchaseOrder";  // JSP 파일 경로 반환
    }
}
