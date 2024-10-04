package com.e3i3.moduerp.productionstock.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;
import com.e3i3.moduerp.productionstock.service.ProductionStockOutService;

@Controller
@RequestMapping("/")
public class ProductionstockOutController {

	@Autowired
    private ProductionStockOutService productionStockOutService;

	// productionStockOut.jsp ��û ó��
	@RequestMapping("/productionStockOut.do")
	public String showProductionStockOut(Model model) {
		List<ProductionStockOutDTO> stockOutList = productionStockOutService.getAllProductionStockOuts();
		model.addAttribute("stockOutList", stockOutList);
		return "productionStock/productionStockOut"; // productionStockOut.jsp
	}

}