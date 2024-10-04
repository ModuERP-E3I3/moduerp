package com.e3i3.moduerp.item.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.item.model.service.ItemSalesStockService;

@Controller
@RequestMapping("/")
public class ItemSalesStockController {

    @Autowired
    private ItemSalesStockService itemService;

    @RequestMapping(value = "/salesInCreate.do", method = RequestMethod.GET)
    public String showSalesStockInCreatePage(HttpSession session, Model model) {
        String bizNumber = (String) session.getAttribute("biz_number");
        
        // 세션에 저장된 사업자번호 로그 출력
        System.out.println("�α����� ������� ����ڹ�ȣ: " + bizNumber);
        
        if (bizNumber != null) {
        	 // biz_number로 item_name 목록을 가져옴
            List<String> itemNames = itemService.getItemNamesByBizNumber(bizNumber);
         // biz_number로 stock_place 목록을 가져옴
            List<String> stockPlaces = itemService.getStockPlacesByBizNumber(bizNumber);

            // 쿼리 결과 로그 출력
            System.out.println("조회된 itemNames: " + itemNames);
            System.out.println("조회된 stockPlaces: " + stockPlaces);
            
            // JSP로 전달할 모델 추가
            model.addAttribute("itemNames", itemNames);
            model.addAttribute("stockPlaces", stockPlaces);
        }
        return "salesStock/salesStockInCreate";  // JSP 파일 이름
    }
}