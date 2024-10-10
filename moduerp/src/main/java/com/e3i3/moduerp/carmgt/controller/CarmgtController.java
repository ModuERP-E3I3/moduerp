package com.e3i3.moduerp.carmgt.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		public String showCreateCarmgtForm(Model model, HttpSession session) {
		    String bizNumber = (String) session.getAttribute("biz_number");
		    
		    // 사원명과 부서명 조회
		    List<String> empNames = CarmgtService.getEmpNamesByBizNumber(bizNumber);
		    List<String> departmentIds = CarmgtService.getDepartmentIdsByBizNumber(bizNumber);
		    
		    // 차량 정보 조회
		    List<CarmgtDto> cars = CarmgtService.getCarsByBizNumber(bizNumber);

		    model.addAttribute("empNames", empNames);
		    model.addAttribute("departmentIds", departmentIds);
		    model.addAttribute("carModels", cars.stream().map(CarmgtDto::getCarModel).collect(Collectors.toList())); // Collectors.toList()로 변경
		    model.addAttribute("carNums", cars.stream().map(CarmgtDto::getCarNum).collect(Collectors.toList())); // Collectors.toList()로 변경
		    
		    return "car/carMgtCreate";
		}
		
		@PostMapping("/insertCarmgt.do")
		public String insertCarmgt(@RequestParam("carModel") String carModel,
				@RequestParam("carNum") String carNum,
				@RequestParam("empName") String empName,
				@RequestParam("departmentId") String departmentId,
				@RequestParam("paymentPlace") String paymentPlace,
				@RequestParam("paymentHistory") String paymentHistory,
				@RequestParam("paymentPrice") int paymentPrice,
				@RequestParam("paymentDate") String paymentDateStr,
				Model model, HttpSession session) {
			String bizNumber = (String) session.getAttribute("biz_number");
			String paymentHistoryCode = bizNumber + "CM" + System.currentTimeMillis();
			LocalDate parsedDate = LocalDate.parse(paymentDateStr);
			LocalTime currentTime = LocalTime.now();
			LocalDateTime combinedDateTime = LocalDateTime.of(parsedDate, currentTime);
			Timestamp paymentDate = Timestamp.valueOf(combinedDateTime);
			
			CarmgtDto carmgtDto = new CarmgtDto();
			carmgtDto.setCarNum(carNum);
			carmgtDto.setCarModel(carModel);
			carmgtDto.setEmpName(empName);
			carmgtDto.setDepartmentId(departmentId);
			carmgtDto.setPaymentPlace(paymentPlace);
			carmgtDto.setBizNumber(bizNumber);
			carmgtDto.setPaymentHistory(paymentHistory);
			carmgtDto.setPaymentPrice(paymentPrice);
			carmgtDto.setPaymentDate(paymentDate);
			carmgtDto.setPaymentHistoryCode(paymentHistoryCode);
			
			CarmgtService.insertCarmgt(carmgtDto);
			
			return "redirect:/carMgt.do";
		}
}
