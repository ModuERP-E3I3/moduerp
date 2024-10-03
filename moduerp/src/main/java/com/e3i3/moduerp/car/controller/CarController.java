package com.e3i3.moduerp.car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.car.model.dto.CarDto;

@Controller
@RequestMapping("/")
public class CarController {


	// carRes.jsp view
	

	

	// map.jsp view
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 특정 도메인만 허용
	@RequestMapping(value = "/map.do", method = RequestMethod.GET)
	public String forwardMap() {
		return "car/map";
	}

	
	@Autowired
	private com.e3i3.moduerp.car.model.service.CarService CarService;
	
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 특정 도메인만 허용
	@RequestMapping(value = "/carRes.do", method = RequestMethod.GET)
	public String carListView(Model model) {
		List<CarDto> carList = CarService.getAllCar();
		model.addAttribute("carList", carList);
		return "car/carRes";
	}


}

