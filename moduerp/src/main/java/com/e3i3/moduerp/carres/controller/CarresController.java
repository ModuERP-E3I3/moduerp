package com.e3i3.moduerp.carres.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.e3i3.moduerp.carres.model.dto.CarresDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Controller
@RequestMapping("/")
public class CarresController {

	@Autowired
	private com.e3i3.moduerp.carres.model.service.CarresService CarresService;

	@RequestMapping(value = "/carresListCreate.do", method = RequestMethod.GET)
	public String carresListCreateView(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// 차량 예약 list
		List<CarresDto> carresList = CarresService.getAllCarres(bizNumber);

		// 페이지당 항목 수
		int carresPerPage = 5;

		int totalCarres = carresList.size();

		int totalPages = (int) Math.ceil((double) totalCarres / carresPerPage);

		int startIndex = (page - 1) * carresPerPage;
		int endIndex = Math.min(startIndex + carresPerPage, totalCarres);

		// 회사 사업자번호(biznumber)가 일치하는 사원명과 부서명 조회
		List<Employee> empNameDepart = CarresService.getEmpNameDepart(bizNumber);

		// 회사 사업자번호(biznumber)가 일치하는 차량 정보 조회
		List<CarresDto> cars = CarresService.getCarsbyBizNumber(bizNumber);

		List<CarresDto> paginatedList = carresList.subList(startIndex, endIndex);

		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("cars", cars);
		model.addAttribute("carresList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "car/carResListCreate";
	}

	@RequestMapping(value = "/carresFilter.do", method = RequestMethod.GET)
	public String forwardCarresFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<CarresDto> carresList = CarresService.getAllCarres(bizNumber);
		// carresList가 null이 아닌 경우, forEach로 출력
		if (carresList != null) {
			carresList.forEach(car -> System.out.println(car));
		} else {
			System.out.println("carresList is null");
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// startDate의 시분초 기본값 설정 (00:00:00)
	    if (startDate != null && !startDate.isEmpty()) {
	        LocalDateTime startDateTime = LocalDateTime.parse(startDate + " 00:00:00", formatter);
	        startDate = startDateTime.format(formatter);  // 포맷된 문자열로 변환
	    }

	    // endDate의 시분초 기본값 설정 (23:59:59)
	    if (endDate != null && !endDate.isEmpty()) {
	        LocalDateTime endDateTime = LocalDateTime.parse(endDate + " 23:59:59", formatter);
	        endDate = endDateTime.format(formatter);  // 포맷된 문자열로 변환
	    }
		
		// 필터링 로직
		if (option != null && filterText != null) {
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				System.out.println("날짜있는거 실행");
				carresList = CarresService.getCarByFilterDate(bizNumber, option, filterText, startDate, endDate);
			} else if (startDate == null || startDate.isEmpty()) {
				System.out.println("날짜없는거 실행");
				carresList = CarresService.getCarByFilter(bizNumber, option, filterText);
			} else {
				System.out.println("실행 못함");
				carresList = CarresService.getAllCarres(bizNumber);
			}
		} else if ((option == null || filterText == null || filterText.isEmpty()) 
		        && startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
		    System.out.println("필터 없이 날짜만 있는 경우 실행");
		    carresList = CarresService.getCarByFilterOnlyDate(bizNumber, startDate, endDate);
		} else if ((option == null || filterText == null || filterText.isEmpty()) 
		        && startDate != null && !startDate.isEmpty()) {
		    System.out.println("startDate만 있는 경우 실행");
		    carresList = CarresService.getCarByFilterStartDate(bizNumber, startDate);
		} else if ((option == null || filterText == null || filterText.isEmpty()) 
		        && endDate != null && !endDate.isEmpty()) {
		    System.out.println("endDate만 있는 경우 실행");
		    carresList = CarresService.getCarByFilterEndDate(bizNumber, endDate);
		} else {
		    System.out.println("기본 전체 조회 실행");
		    carresList = CarresService.getAllCarres(bizNumber);
		}

		int carresPerPage = 5;
		int totalCarres = carresList.size();
		int totalPages = (int) Math.ceil((double) totalCarres / carresPerPage);
		int startIndex = (page - 1) * carresPerPage;
		int endIndex = Math.min(startIndex + carresPerPage, totalCarres);

		List<CarresDto> paginatedList = carresList.subList(startIndex, endIndex);

		model.addAttribute("carresList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "car/carResListFilter";
	}

	@PostMapping("/insertCarres.do")
	public String inserCarres(@RequestParam("carModel") String carModel, @RequestParam("carNum") String carNum,
			@RequestParam("empName") String empName, @RequestParam("departmentId") String departmentId,
			@RequestParam("reserveStartDate") String reserveStartDateStr,
			@RequestParam("reserveEndDate") String reserveEndDateStr, @RequestParam("useReason") String useReason,
			@RequestParam("drivingStatus") String drivingStatus,
			@RequestParam(value = "page", defaultValue = "1") int page,
			RedirectAttributes redirectAttributes,
			Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String carReserveCode = bizNumber + "CR" + System.currentTimeMillis();

		LocalDateTime parseStartDate = LocalDateTime.parse(reserveStartDateStr);
		LocalDateTime parseEndDate = LocalDateTime.parse(reserveEndDateStr);
		Timestamp reserveStartDate = Timestamp.valueOf(parseStartDate);
		Timestamp reserveEndDate = Timestamp.valueOf(parseEndDate);

		// 중복 예약 체크: 입력된 차량 모델, 번호와 겹치는 예약 내역 중에 날짜가 겹치는지 확인
		List<CarresDto> overlappingReservations = CarresService.getOverlappingReservations(carModel, carNum, reserveStartDate, reserveEndDate, bizNumber);
		List<CarresDto> carresList = CarresService.getAllCarres(bizNumber);
		int carresPerPage = 5;
		int totalCarres = carresList.size();
		int totalPages = (int) Math.ceil((double) totalCarres / carresPerPage);
		int startIndex = (page - 1) * carresPerPage;
		int endIndex = Math.min(startIndex + carresPerPage, totalCarres);
		// 회사 사업자번호(biznumber)가 일치하는 사원명과 부서명 조회
		List<Employee> empNameDepart = CarresService.getEmpNameDepart(bizNumber);
		// 회사 사업자번호(biznumber)가 일치하는 차량 정보 조회
		List<CarresDto> cars = CarresService.getCarsbyBizNumber(bizNumber);
		List<CarresDto> paginatedList = carresList.subList(startIndex, endIndex);
	    if (!overlappingReservations.isEmpty()) {
	        // 경고 메시지를 전달
	        model.addAttribute("alertMessage", "이미 예약된 차량입니다. 다른 차량을 선택하거나 다른 날짜를 선택해 주세요.");
	        model.addAttribute("empNameDepart", empNameDepart);
			model.addAttribute("cars", cars);
			model.addAttribute("carresList", paginatedList);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", page);
	        return "car/carResListCreate";  // JSP 페이지로 이동
	    }
		
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

	// 차량 예약 상세 페이지
	@GetMapping("getCarresDetail.do")
	public String getCarresDetail(@RequestParam("carReserveCode") String carReserveCode, Model model) {
		CarresDto carresDetail = CarresService.getCarresListDetail(carReserveCode);

		model.addAttribute("carresDetail", carresDetail);

		return "car/carResListDetail";
	}

	// 차량 예약 수정 페이지로 이동
	@GetMapping("carresDetailUpdate.do")
	public String carresDetailUpdate(@RequestParam("carReserveCode") String carReserveCode, Model model,
			HttpSession session) {
		CarresDto carresDetail = CarresService.getCarresListDetail(carReserveCode);
		String bizNumber = (String) session.getAttribute("biz_number");

		// 사원 정보 조회
		List<Employee> empNameDepart = CarresService.getEmpNameDepart(bizNumber);

		// 차량 정보 조회
		List<CarresDto> cars = CarresService.getCarsbyBizNumber(bizNumber);

		model.addAttribute("cars", cars); // Collectors.toList()로 변경
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("carresDetail", carresDetail);

		return "car/carResListDetailUpdate";
	}

	// 차량 예약 수정
	@PostMapping("/updateCarres.do")
	public String updateCarres(@RequestParam("carId") String carId, @RequestParam("uuid") String uuid,
			@RequestParam("carModel") String carModel, @RequestParam("carNum") String carNum,
			@RequestParam("empName") String empName, @RequestParam("departmentId") String departmentId,
			@RequestParam("reserveStartDate") String reserveStartDateStr,
			@RequestParam("reserveEndDate") String reserveEndDateStr, @RequestParam("useReason") String useReason,
			@RequestParam("drivingStatus") String drivingStatus,
			@RequestParam("carReserveCode") String carReserveCode) {
		LocalDateTime parseStartDate = LocalDateTime.parse(reserveStartDateStr);
		LocalDateTime parseEndDate = LocalDateTime.parse(reserveEndDateStr);
		Timestamp reserveStartDate = Timestamp.valueOf(parseStartDate);
		Timestamp reserveEndDate = Timestamp.valueOf(parseEndDate);

		CarresDto carresDto = new CarresDto();
		carresDto.setCarId(carId);
		carresDto.setUuid(uuid);
		carresDto.setCarNum(carNum);
		carresDto.setCarModel(carModel);
		carresDto.setEmpName(empName);
		carresDto.setDepartmentId(departmentId);
		carresDto.setReserveStartDate(reserveStartDate);
		carresDto.setReserveEndDate(reserveEndDate);
		carresDto.setUseReason(useReason);
		carresDto.setCarReserveCode(carReserveCode);
		carresDto.setDrivingStatus(drivingStatus);

		CarresService.updateCarres(carresDto);

		return "redirect:/carresListCreate.do";
	}

	@PostMapping("/deleteCarres.do")
	public String deleteCarres(@RequestParam("carReserveCode") String carReserveCode, HttpSession session) {
		CarresService.deleteCarres(carReserveCode);

		return "redirect:/carresListCreate.do";
	}

}
