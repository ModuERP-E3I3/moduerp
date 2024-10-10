package com.e3i3.moduerp.carmgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;

@Controller
@RequestMapping("/")
public class CarmgtController {
		
		@Autowired
		private com.e3i3.moduerp.carmgt.model.service.CarmgtService CarmgtService;
		
		@RequestMapping(value = "/carMgt.do", method = RequestMethod.GET)
		public String forwardCarMgt(Model model) {
			List<CarmgtDto> carmgtList = CarmgtService.getAllCarmgt();
			model.addAttribute("carmgtList", carmgtList);
			return "car/carMgt";
		}
		
		@RequestMapping(value = "/carmgtCreate.do", method = RequestMethod.GET)
		public String showCreateCarmgtForm() {
			return "car/carMgtCreate";
		}
}
