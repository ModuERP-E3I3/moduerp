package com.e3i3.moduerp.productionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.productionstock.dto.ProductionStockInDTO;
import com.e3i3.moduerp.productionstock.service.ProductionStockInService;

@Controller
@RequestMapping("/")
public class ProductionstockController {

	@Autowired
    private ProductionStockInService productionStockInService;

    @RequestMapping(value = "/productionStockIn.do", method = RequestMethod.GET)
    public String forwardProductionIn(Model model) {
        List<ProductionStockInDTO> stockList = productionStockInService.getAllProductionStockIn();
        model.addAttribute("stockList", stockList);
        return "productionStock/productionStockIn";  // JSP 파일 경로 반환
    }

    // productionStockOut.jsp 요청 처리
    @RequestMapping(value = "/productionStockOut.do", method = RequestMethod.GET)
    public String forwardProductionOut() {
        return "productionStock/productionStockOut";  // JSP 파일 경로 반환
    }
    
 // productionWorkorder.jsp 요청 처리
    @RequestMapping(value = "/productionWorkorder.do", method = RequestMethod.GET)
    public String forwardProductionWorkorder() {
        return "productionStock/productionWorkorder";  // JSP 파일 경로 반환
    }

    // productionQuality.jsp 요청 처리
    @RequestMapping(value = "/productionQuality.do", method = RequestMethod.GET)
    public String forwardProductionQuality() {
        return "productionStock/productionQuality";  // JSP 파일 경로 반환
    }
}