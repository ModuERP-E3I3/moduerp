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
        
        // ���ǿ� ����� ����ڹ�ȣ �α� ���
        System.out.println("�α����� ������� ����ڹ�ȣ: " + bizNumber);
        
        if (bizNumber != null) {
            // biz_number�� item_name ����� ������
            List<String> itemNames = itemService.getItemNamesByBizNumber(bizNumber);
            // biz_number�� stock_place ����� ������
            List<String> stockPlaces = itemService.getStockPlacesByBizNumber(bizNumber);

            // ���� ��� �α� ���
            System.out.println("��ȸ�� itemNames: " + itemNames);
            System.out.println("��ȸ�� stockPlaces: " + stockPlaces);
            
            // JSP�� ������ �� �߰�
            model.addAttribute("itemNames", itemNames);
            model.addAttribute("stockPlaces", stockPlaces);
        }
        return "salesStock/salesStockInCreate"; // JSP ���� �̸�
    }
}
