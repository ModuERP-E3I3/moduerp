package com.e3i3.moduerp.carres.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.carres.model.dto.CarresDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Controller
@RequestMapping("/")
public class CarresController {

	@Autowired
	private com.e3i3.moduerp.carres.model.service.CarresService CarresService;
	
	@RequestMapping(value = "/carresListCreate.do", method = RequestMethod.GET)
	public String carresListCreateView(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		
		// 차량 예약 list
		List<CarresDto> carresList = CarresService.getAllCarres();
		
		// 회사 사업자번호(biznumber)가 일치하는 사원명과 부서명 조회
		List<Employee> empNameDepart = CarresService.getEmpNameDepart(bizNumber);
		
		// 회사 사업자번호(biznumber)가 일치하는 차량 정보 조회
		List<CarresDto> cars = CarresService.getCarsbyBizNumber(bizNumber);
		
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("cars", cars);
		model.addAttribute("carresList", carresList);
		
		return "car/carResListCreate";
	}
	
	@PostMapping("/insertCarres.do")
	public String inserCarres(@RequestParam("carModel") String carModel,
			@RequestParam("carNum") String carNum,
			@RequestParam("empName") String empName,
			@RequestParam("departmentId") String departmentId,
			@RequestParam("reserveStartDate") String reserveStartDateStr,
			@RequestParam("reserveEndDate") String reserveEndDateStr,
			@RequestParam("useReason") String useReason,
			@RequestParam("drivingStatus") String drivingStatus,
			Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String carReserveCode = bizNumber + "CR" + System.currentTimeMillis();
		LocalDate parseStartDate = LocalDate.parse(reserveStartDateStr);
		LocalDate parseEndDate = LocalDate.parse(reserveEndDateStr);
		LocalTime currentTime = LocalTime.now();
		LocalDateTime combinedStartDateTime = LocalDateTime.of(parseStartDate, currentTime);
		LocalDateTime combinedEndDateTime = LocalDateTime.of(parseEndDate, currentTime);
		Timestamp reserveStartDate = Timestamp.valueOf(combinedStartDateTime);
		Timestamp reserveEndDate = Timestamp.valueOf(combinedEndDateTime);
		
		CarresDto carresDto = new CarresDto();
		carresDto.setCarNum(carNum);
		carresDto.setCarModel(carModel);
		carresDto.setEmpName(empName);
		carresDto.setDepartmentId(departmentId);
		carresDto.setReserveStartDate(reserveStartDate);
		carresDto.setReserveEndDate(reserveEndDate);
		carresDto.setUseReason(useReason);
		carresDto.setBizNumber(bizNumber);
		carresDto.setCarReserveCode(carReserveCode);
		carresDto.setDrivingStatus(drivingStatus);
		
		CarresService.insertCarres(carresDto);
		
		return "redirect:/carresListCreate.do";
	}
	
}
