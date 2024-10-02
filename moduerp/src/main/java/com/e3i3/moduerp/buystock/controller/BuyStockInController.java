package com.e3i3.moduerp.buystock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.e3i3.moduerp.buystock.model.dto.BuyStockInDTO;

@Controller
@RequestMapping("/")
public class BuyStockInController {
	

		@Autowired
		private com.e3i3.moduerp.buystock.service.BuyStockInService BuyStockInService;

		@RequestMapping(value = "/buyStockIn.do", method = RequestMethod.GET)
		public String forwardBuyIn(Model model) {
			List<BuyStockInDTO> stockList = BuyStockInService.getAllBuyStockIn();
			model.addAttribute("stockList", stockList);
			
			return "buyStock/buyStockIn"; // JSP 파일 경로 반환
		}

	
}
