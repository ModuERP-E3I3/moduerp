package com.e3i3.moduerp.item.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.employee.model.service.EmployeeProductionService;
import com.e3i3.moduerp.item.model.service.ItemProductionstockService;

@Controller
@RequestMapping("/")
public class ItemProductionstockController {

    @Autowired
    private ItemProductionstockService itemService;
    
	@Autowired
	private EmployeeProductionService employeeProductionService;

    @RequestMapping(value = "/productionInCreate.do", method = RequestMethod.GET)
    public String showProductionStockInCreatePage(HttpSession session, Model model) {
        String bizNumber = (String) session.getAttribute("biz_number");
        String uuid = (String) session.getAttribute("uuid");
        
        // ���ǿ� ����� ����ڹ�ȣ �α� ���
        System.out.println("�α����� ������� ����ڹ�ȣ: " + bizNumber);
        
        if (bizNumber != null) {
            // biz_number�� item_name ����� ������
            List<String> itemNames = itemService.getItemNamesByBizNumber(bizNumber);
            // biz_number�� stock_place ����� ������
            List<String> stockPlaces = itemService.getStockPlacesByBizNumber(bizNumber);

            String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);
            // ���� ��� �α� ���
            System.out.println("��ȸ�� itemNames: " + itemNames);
            System.out.println("��ȸ�� stockPlaces: " + stockPlaces);
            
            // JSP�� ������ �� �߰�
            model.addAttribute("itemNames", itemNames);
            model.addAttribute("stockPlaces", stockPlaces);
            model.addAttribute("directorName", directorName);
        }
        return "productionStock/productionStockInCreate"; // JSP ���� �̸�
    }
}
