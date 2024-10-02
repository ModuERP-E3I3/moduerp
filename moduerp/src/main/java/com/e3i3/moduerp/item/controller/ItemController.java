package com.e3i3.moduerp.item.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.item.model.service.ItemService;

@Controller
@RequestMapping("/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/productionInCreate.do", method = RequestMethod.GET)
    public String showProductionStockInCreatePage(HttpSession session, Model model) {
        String bizNumber = (String) session.getAttribute("biz_number");
        
        // 세션에 저장된 사업자번호 로그 출력
        System.out.println("로그인한 사용자의 사업자번호: " + bizNumber);
        
        if (bizNumber != null) {
            List<String> itemNames = itemService.getItemNamesByBizNumber(bizNumber);
            
            // 쿼리 결과 로그 출력
            System.out.println("조회된 itemNames: " + itemNames);
            
            model.addAttribute("itemNames", itemNames);
        }
        return "productionStock/productionStockInCreate"; // JSP 파일 이름
    }

}
