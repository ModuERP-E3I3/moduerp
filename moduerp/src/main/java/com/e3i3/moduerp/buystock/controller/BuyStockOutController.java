package com.e3i3.moduerp.buystock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.e3i3.moduerp.buystock.model.dto.BuyStockOutDTO;
import com.e3i3.moduerp.buystock.service.BuyStockOutService;



@Controller
@RequestMapping("/")
public class BuyStockOutController {

	@Autowired
    private BuyStockOutService buyStockOutService;

	@RequestMapping("/buyStockOut.do")
	public String showBuyStockOut(Model model) {
		List<BuyStockOutDTO> stockOutList = buyStockOutService.getAllBuyStockOuts();
		model.addAttribute("stockOutList", stockOutList);
		return "buyStock/buyStockOut"; 
	}
	
}
