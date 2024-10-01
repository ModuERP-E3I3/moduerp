package com.e3i3.moduerp.quality.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.quality.model.dto.QualityControlDTO;
import com.e3i3.moduerp.quality.model.service.QualityControlService;

@Controller
@RequestMapping("/")
public class QualityControlController {

	@Autowired
	private QualityControlService qualityControlService;

	@RequestMapping(value ="/productionQuality.do", method = RequestMethod.GET)
	public String getAllQualityControls(Model model) {
		List<QualityControlDTO> qualityControlList = qualityControlService.getAllQualityControls();
		model.addAttribute("qualityControlList", qualityControlList);
		return "productionStock/productionQuality";
	}

}
