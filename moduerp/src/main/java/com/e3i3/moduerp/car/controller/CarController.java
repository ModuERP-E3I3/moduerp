package com.e3i3.moduerp.car.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;

import com.e3i3.moduerp.car.model.dto.CarDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static final Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@Autowired
	private com.e3i3.moduerp.car.model.service.CarService CarService;
	
	/*
	 * @Autowired private com.e3i3.moduerp.carres.model.service.CarresService
	 * CarresService;
	 */
	
	@Autowired 
	private ServletContext servletContext;
		 
	
	
	
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 
	@RequestMapping(value = "/carRes.do", method = RequestMethod.GET)
	public String carListView(Model model, HttpSession session) {
		
		String bizNumber = (String) session.getAttribute("biz_number");
		
		List<CarDto> carList = CarService.getAllCar(bizNumber);
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
                            @RequestParam("image") MultipartFile image,
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

     // 이미지 저장 경로 설정
        String relativePath = "resources/images/carimg"; // 상대 경로
        String realPath = servletContext.getRealPath(relativePath); // 실제 경로
        String fileName = bizNumber + currentTimestampKST.getTime() + "_" + image.getOriginalFilename(); // 파일명 변경

        // 파일 저장
        File file = new File(realPath, fileName);
        try {
            image.transferTo(file);
            carDto.setImagePath(relativePath + "/" + fileName); // DB에 저장할 경로
        } catch (IOException e) {
            e.printStackTrace();
            // 예외 처리 로직 추가
            return "redirect:/carCreate.do"; // 예외 발생 시 다시 폼으로 돌아감
        }

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
                            @RequestParam("ownershipStatus") String ownershipStatus,
                            @RequestParam(value = "image", required = false) MultipartFile image,
                            HttpSession session) {

        CarDto carDto = new CarDto(); 
        carDto.setCarId(carId);
        carDto.setCarNum(carNum);
        carDto.setCarModel(carModel); 
        carDto.setOwnershipStatus(ownershipStatus);

        // 이미지 저장 경로 설정
        String relativePath = "resources/images/carimg"; // 상대 경로
        String realPath = servletContext.getRealPath(relativePath); // 실제 경로
        String fileName = null;

        // 파일이 업로드 되었을 경우
        if (image != null && !image.isEmpty()) {
            ZonedDateTime nowKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
            Timestamp currentTimestampKST = Timestamp.valueOf(nowKST.toLocalDateTime());

            // biz_number를 문자열로 변환하여 파일명 생성
            fileName = session.getAttribute("biz_number").toString() + currentTimestampKST.getTime() + "_" + image.getOriginalFilename(); // 파일명 변경

            // 파일 저장
            File file = new File(realPath, fileName);
            try {
                image.transferTo(file);
                carDto.setImagePath(relativePath + "/" + fileName); // DB에 저장할 경로
            } catch (IOException e) {
                e.printStackTrace();
                // 예외 발생 시 다시 수정 페이지로 돌아감
                return "redirect:/carDetailUpdate.do?carId=" + carId; 
            }
        } else {
            // 이미지가 없을 경우 기존 이미지 경로 유지
            CarDto existingCar = CarService.getCarListDetail(carId);
            carDto.setImagePath(existingCar.getImagePath()); // 기존 이미지 경로를 설정
        }

        // 로그 출력
        logger.info("업데이트하려는 차량 ID: " + carId);
        logger.info("업데이트하려는 차량 번호: " + carNum);
        logger.info("업데이트하려는 차량 모델: " + carModel);
        logger.info("업데이트하려는 이미지 경로: " + carDto.getImagePath());
        
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