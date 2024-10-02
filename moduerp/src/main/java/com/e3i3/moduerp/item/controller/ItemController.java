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
        
        // ���ǿ� ����� ����ڹ�ȣ �α� ���
        System.out.println("�α����� ������� ����ڹ�ȣ: " + bizNumber);
        
        if (bizNumber != null) {
            List<String> itemNames = itemService.getItemNamesByBizNumber(bizNumber);
            
            // ���� ��� �α� ���
            System.out.println("��ȸ�� itemNames: " + itemNames);
            
            model.addAttribute("itemNames", itemNames);
        }
        return "productionStock/productionStockInCreate"; // JSP ���� �̸�
    }

}
