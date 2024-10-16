package com.e3i3.moduerp.carmgt.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Controller
@RequestMapping("/")
public class CarmgtController {

	@Autowired
	private com.e3i3.moduerp.carmgt.model.service.CarmgtService CarmgtService;

	@RequestMapping(value = "/carMgt.do", method = RequestMethod.GET)
	public String forwardCarMgt(@RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		
		List<CarmgtDto> carmgtList = CarmgtService.getAllCarmgt(bizNumber);
		
		// 페이지당 항목 수
		int carmgtPerPage = 10;
		
		int totalCarmgt = carmgtList.size();
		
		int totalPages = (int) Math.ceil((double) totalCarmgt / carmgtPerPage);
		
		int startIndex = (page - 1) * carmgtPerPage;
		int endIndex = Math.min(startIndex + carmgtPerPage, totalCarmgt);
		
		List<CarmgtDto> paginatedList = carmgtList.subList(startIndex, endIndex);
		
		
		model.addAttribute("carmgtList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		return "car/carMgt";
	}
	
	@RequestMapping(value = "/carmgtFilter.do",  method = RequestMethod.GET)
	public String forwardCarmgtFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<CarmgtDto> carmgtList = CarmgtService.getAllCarmgt("bizNumber");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		if (startDate != null && !startDate.isEmpty()) {
		    try {
		        // 시간이 없는 경우 기본 시간 00:00:00을 추가
		        if (startDate.length() == 10) { // 'yyyy-MM-dd' 형식
		            startDate += " 00:00:00";
		        }
		        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
		        startDate = startDateTime.format(formatter);  // 포맷된 문자열로 변환
		    } catch (DateTimeParseException e) {
		        // 파싱 에러 처리
		        System.out.println("Invalid startDate format: " + startDate);
		    }
		}

		// endDate의 시분초 기본값 설정 (23:59:59)
		if (endDate != null && !endDate.isEmpty()) {
		    try {
		        // 시간이 없는 경우 기본 시간 23:59:59을 추가
		        if (endDate.length() == 10) { // 'yyyy-MM-dd' 형식
		            endDate += " 23:59:59";
		        }
		        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
		        endDate = endDateTime.format(formatter);  // 포맷된 문자열로 변환
		    } catch (DateTimeParseException e) {
		        // 파싱 에러 처리
		        System.out.println("Invalid endDate format: " + endDate);
		    }
		}
		
		// 필터링 로직
				if (option != null && filterText != null) {
					if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
						System.out.println("날짜있는거 실행");
						carmgtList = CarmgtService.getCarByFilterDate(bizNumber, option, filterText, startDate, endDate);
					} else if (startDate == null || startDate.isEmpty()) {
						System.out.println("날짜없는거 실행");
						carmgtList = CarmgtService.getCarByFilter(bizNumber, option, filterText);
					} else {
						System.out.println("실행 못함");
						carmgtList = CarmgtService.getAllCarmgt(bizNumber);
					}
				} else if ((option == null || filterText == null || filterText.isEmpty()) 
				        && startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				    System.out.println("필터 텍스트 없이 날짜만 있는 경우 실행");
				    carmgtList = CarmgtService.getCarByFilterOnlyDate(bizNumber, startDate, endDate);
				} else if ((option == null || filterText == null || filterText.isEmpty()) 
				        && startDate != null && !startDate.isEmpty()) {
				    System.out.println("startDate만 있는 경우 실행");
				    carmgtList = CarmgtService.getCarByFilterStartDate(bizNumber, startDate);
				} else if ((option == null || filterText == null || filterText.isEmpty()) 
				        && endDate != null && !endDate.isEmpty()) {
				    System.out.println("endDate만 있는 경우 실행");
				    carmgtList = CarmgtService.getCarByFilterEndDate(bizNumber, endDate);
				} else {
				    System.out.println("기본 전체 조회 실행");
				    carmgtList = CarmgtService.getAllCarmgt(bizNumber);
				}
				
				int carmgtPerPage = 10;
				int totalCarmgt = carmgtList.size();
				int totalPages = (int) Math.ceil((double) totalCarmgt / carmgtPerPage);
				int startIndex = (page - 1) * carmgtPerPage;
				int endIndex = Math.min(startIndex + carmgtPerPage, totalCarmgt);
				
				List<CarmgtDto> paginatedList = carmgtList.subList(startIndex, endIndex);
				
				
				model.addAttribute("carmgtList", paginatedList);
				model.addAttribute("totalPages", totalPages);
				model.addAttribute("currentPage", page);
				model.addAttribute("option", option);
				model.addAttribute("filterText", filterText);
				model.addAttribute("startDate", startDate);
				model.addAttribute("endDate", endDate);
		
				return "car/carMgtFilter";
	}
	
	

	@RequestMapping(value = "/carmgtCreate.do", method = RequestMethod.GET)
	public String showCreateCarmgtForm(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// 사원명과 부서명 조회
		List<String> empNames = CarmgtService.getEmpNamesByBizNumber(bizNumber);
		List<String> departmentIds = CarmgtService.getDepartmentIdsByBizNumber(bizNumber);

		List<Employee> empNameDepart = CarmgtService.getEmpNameDepart(bizNumber);

		// 차량 정보 조회
		List<CarmgtDto> cars = CarmgtService.getCarsByBizNumber(bizNumber);

		model.addAttribute("empNames", empNames);
		model.addAttribute("departmentIds", departmentIds);
		model.addAttribute("cars", cars); // Collectors.toList()로 변경
		model.addAttribute("empNameDepart", empNameDepart);
//		    model.addAttribute("carNums", cars.stream().map(CarmgtDto::getCarNum).collect(Collectors.toList())); // Collectors.toList()로 변경

		return "car/carMgtCreate";
	}

	@PostMapping("/insertCarmgt.do")
	public String insertCarmgt(@RequestParam("carModel") String carModel, @RequestParam("carNum") String carNum,
			@RequestParam("empName") String empName, @RequestParam("departmentId") String departmentId,
			@RequestParam("paymentPlace") String paymentPlace, @RequestParam("paymentHistory") String paymentHistory,
			@RequestParam("paymentPrice") int paymentPrice, @RequestParam("paymentDate") String paymentDateStr,
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

	// 차량 결제 관리 상세 페이지
	@GetMapping("getCarmgtDetail.do")
	public String getCarmgtDetail(@RequestParam("paymentHistoryCode") String paymentHistoryCode, Model model) {
		CarmgtDto carmgtDetail = CarmgtService.getCarmgtListDetail(paymentHistoryCode);

		model.addAttribute("carmgtDetail", carmgtDetail);

		return "car/carMgtDetail";
	}

	// 차량 결제 관리 수정 페이지로 이동
	@GetMapping("carmgtDetailUpdate.do")
	public String carmgtDetailUpdate(@RequestParam("paymentHistoryCode") String paymentHistoryCode, Model model,
			HttpSession session) {
		CarmgtDto carmgtDetail = CarmgtService.getCarmgtListDetail(paymentHistoryCode);

		String bizNumber = (String) session.getAttribute("biz_number");

		List<Employee> empNameDepart = CarmgtService.getEmpNameDepart(bizNumber);

		// 차량 정보 조회
		List<CarmgtDto> cars = CarmgtService.getCarsByBizNumber(bizNumber);

		model.addAttribute("cars", cars); // Collectors.toList()로 변경
		model.addAttribute("empNameDepart", empNameDepart);

		model.addAttribute("carmgtDetail", carmgtDetail);

		return "car/carMgtDetailUpdate";
	}

	@PostMapping("/updateCarmgt.do")
	public String updateCarmgt(@RequestParam("carId") String carId, @RequestParam("carModel") String carModel,
			@RequestParam("carNum") String carNum, @RequestParam("empName") String empName,
			@RequestParam("departmentId") String departmentId, @RequestParam("paymentPlace") String paymentPlace,
			@RequestParam("paymentHistory") String paymentHistory, @RequestParam("paymentPrice") int paymentPrice,
			@RequestParam("paymentDate") String paymentDateStr, @RequestParam("uuid") String uuid,
			@RequestParam("paymentHistoryCode") String paymentHistoryCode) {
		LocalDate parsedDate = LocalDate.parse(paymentDateStr);
		LocalTime currentTime = LocalTime.now();
		LocalDateTime combinedDateTime = LocalDateTime.of(parsedDate, currentTime);
		Timestamp paymentDate = Timestamp.valueOf(combinedDateTime);

		CarmgtDto carmgtDto = new CarmgtDto();
		carmgtDto.setCarId(carId);
		carmgtDto.setCarNum(carNum);
		carmgtDto.setCarModel(carModel);
		carmgtDto.setEmpName(empName);
		carmgtDto.setDepartmentId(departmentId);
		carmgtDto.setPaymentPlace(paymentPlace);
		carmgtDto.setPaymentHistory(paymentHistory);
		carmgtDto.setPaymentPrice(paymentPrice);
		carmgtDto.setPaymentDate(paymentDate);
		carmgtDto.setUuid(uuid);
		carmgtDto.setPaymentHistoryCode(paymentHistoryCode);

		CarmgtService.updateCarmgt(carmgtDto);

		return "redirect:/carMgt.do";
	}
	
	@PostMapping("/deleteCarmgt.do")
	public String deleteCarmgt(@RequestParam("paymentHistoryCode") String paymentHistoryCode, HttpSession session) {
		CarmgtService.deleteCarmgt(paymentHistoryCode);
		
		return "redirect:/carMgt.do";
	}

}
