package com.e3i3.moduerp.delivery.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.buystock.model.service.BuyStockInService;
import com.e3i3.moduerp.delivery.model.service.DeliveryBuyStockService;
import com.e3i3.moduerp.delivery.model.service.DeliveryProductionStockService;
import com.e3i3.moduerp.delivery.model.service.DeliverySalesStockService;
import com.e3i3.moduerp.delivery.model.service.DeliveryService;
import com.e3i3.moduerp.employee.model.service.EmployeeBuyService;
import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemBuyStockService;

public class DeliveryController {
	
	@Autowired
	private DeliveryService itembuyStockService;

	@RequestMapping(value = "/buyStockIn.do", method = RequestMethod.GET)
	public String forwardBuyIn(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
	        HttpSession session) {
	    String bizNumber = (String) session.getAttribute("biz_number");
	   

	    List<ItemDTO> itemList = itembuyStockService.getAllItemsByBizNumber(bizNumber);
	   
	    

	    for (ItemDTO item : itemList) {
	        if (item.getCreatedAt() != null) {
	            Timestamp createdAt = item.getCreatedAt();
	            Timestamp adjustedTimestamp = Timestamp
	                    .from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));
	            item.setCreatedAt(adjustedTimestamp);
	        }
	    }

	    int itemsPerPage = 10;
	    int totalItems = itemList.size();
	    int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
	    int startIndex = (page - 1) * itemsPerPage;
	    int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
	    List<ItemDTO> paginatedList = itemList.subList(startIndex, endIndex);

	    model.addAttribute("itemList", paginatedList);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("currentPage", page);

	    return "delivery/delivery"; // JSP
	}
	
	
	
}
