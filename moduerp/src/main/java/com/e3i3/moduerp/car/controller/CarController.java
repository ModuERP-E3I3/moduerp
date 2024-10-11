package com.e3i3.moduerp.car.controller;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.car.model.dto.CarDto;
import com.e3i3.moduerp.carres.model.dto.CarresDto;

@Controller
@RequestMapping("/")
public class CarController {


	// carRes.jsp view
	

	

	// map.jsp view
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // Ư�� �����θ� ���
	@RequestMapping(value = "/map.do", method = RequestMethod.GET)
	public String forwardMap() {
		return "car/map";
	}

	
	@Autowired
	private com.e3i3.moduerp.car.model.service.CarService CarService;
	
	/*
	 * @Autowired private com.e3i3.moduerp.carres.model.service.CarresService
	 * CarresService;
	 */
	
	
	
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 
	@RequestMapping(value = "/carRes.do", method = RequestMethod.GET)
	public String carListView(Model model) {
		List<CarDto> carList = CarService.getAllCar();
		model.addAttribute("carList", carList);
		return "car/carRes";
	}
	
	// 차량 추가 페이지로 이동
    @RequestMapping(value = "/carCreate.do", method = RequestMethod.GET)
    public String showCreateCarForm() {
        return "car/carCreate";  // carCreate.jsp로 이동
    }
    
    /*
    // 차량 추가 처리
    @RequestMapping(value = "/insertCar.do", method = RequestMethod.POST)
    public String insertCar(CarDto carDto) {
        CarService.insertCar(carDto);  // Service 호출
        return "redirect:/carRes.do";  // 차량 목록 페이지로 리다이렉트
    }
    */
    @PostMapping("/insertCar.do")
    public String insertCar(@RequestParam("carNum") String carNum,
			  @RequestParam("carModel") String carModel,
			  @RequestParam("ownershipStatus") String ownershipStatus,
			  HttpSession session) {
    	String bizNumber = (String) session.getAttribute("biz_number");
    	
    	ZonedDateTime nowKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
		Timestamp currentTimestampKST = Timestamp.valueOf(nowKST.toLocalDateTime());
		
		// ITEM_CODE 생성: biz_number + "C" + 현재 타임스탬프
		String carId = bizNumber + "C" + currentTimestampKST.getTime();
		
		CarDto carDto = new CarDto();
		carDto.setCarId(carId);
		carDto.setCarNum(carNum);
		carDto.setCarModel(carModel);
		carDto.setOwnershipStatus(ownershipStatus);
		carDto.setBizNumber(bizNumber);
		
		CarService.insertCar(carDto);
		
		return "redirect:/carRes.do";
    
    }
    
    
    
    // 차량 상세 페이지
    @GetMapping("/getCarDetail.do")
    public String getCarDetail(@RequestParam("carId") String carId, Model model) {
    	CarDto carDetail = CarService.getCarListDetail(carId);
    	
    	model.addAttribute("carDetail", carDetail);
    	
    	return "car/carDetail";
    }
    
    // 차량 정보 수정 페이지로 이동
    @GetMapping("/carDetailUpdate.do")
    public String carDetailUpdate(@RequestParam("carId") String carId, Model model) {
    	CarDto carDetail = CarService.getCarListDetail(carId);
    	
    	model.addAttribute("carDetail", carDetail);
    	
    	return "car/carDetailUpdate";
    }
    
    
	  @PostMapping("/updateCar.do") 
	  public String updateCar(@RequestParam("carId") String carId,
			  @RequestParam("carNum") String carNum,
			  @RequestParam("carModel") String carModel,
			  @RequestParam("ownershipStatus") String ownershipStatus) {
	  
		  CarDto carDto = new CarDto(); 
		  
		  carDto.setCarId(carId);
		  carDto.setCarNum(carNum);
		  carDto.setCarModel(carModel); 
		  carDto.setOwnershipStatus(ownershipStatus);
	  
		  CarService.updateCar(carDto);
	  
		  return "redirect:/carRes.do"; 
	  }
	 
	  @PostMapping("/deleteCar.do")
	  public String deleteCar(@RequestParam("carId") String carId
			) {
		  
		 CarDto carDto = new CarDto();
		 
		 carDto.setCarId(carId);
		 
		 CarService.deleteCar(carDto);
		  
		  return "redirect:/carRes.do";
	  }
	
	 
	 
	

}

