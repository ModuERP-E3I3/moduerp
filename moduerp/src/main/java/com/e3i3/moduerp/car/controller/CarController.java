package com.e3i3.moduerp.car.controller;

import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.car.model.dto.Car;
import com.e3i3.moduerp.car.model.service.CarService;
import com.e3i3.moduerp.notice.model.dto.Notice;

@Controller
@RequestMapping("/")
public class CarController {


	// carRes.jsp view
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 특정 도메인만 허용
	@RequestMapping(value = "/carRes.do", method = RequestMethod.GET)
	public String forwardCarRes() {
		return "car/carRes";
	}

	// carMgt.jsp view
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 특정 도메인만 허용
	@RequestMapping(value = "/carMgt.do", method = RequestMethod.GET)
	public String forwardCarMgt() {
		return "car/carMgt";
	}

	// map.jsp view
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 특정 도메인만 허용
	@RequestMapping(value = "/map.do", method = RequestMethod.GET)
	public String forwardMap() {
		return "car/map";
	}

	@RequestMapping(value = "carlist.do", method = RequestMethod.POST)
	public String carList(HttpServletResponse response) {

		ArrayList<Car> list = CarService.selectTop10();

		response.setContentType("application/json; charset=utf-8");

		JSONArray jarr = new JSONArray();

		for (Car car : list) {
			JSONObject job = new JSONObject(); // org.json.simple.JSONObject 임포트함

			job.put("carId", URLEncoder.encode(car.getCarId(), "utf-8"));
			// 문자열값에 한글이 포함되어 있다면, 반드시 인코딩해서 저장해야 함
			// java.net.URLEncoder 의 static 메소드인 encode('한글이있는문자열값', '문자셋') 사용함
			job.put("carModel", URLEncoder.encode(car.getCarModel(), "utf-8"));
			// 날짜데이터는 반드시 문자열로 바꿔서 저장할 것 : 날짜 그대로 저장하면 뷰에서 json 전체 출력 안 됨
			job.put("ownershipStatus", URLEncoder.encode(car.getOwnershipStatus(), "utf-8"));

			jarr.add(job); // 배열에 추가
		}
		// 전송용 json 객체 생성함
		JSONObject sendJson = new JSONObject();
		// 전송용 json 에 jarr 을 저장함
		sendJson.put("nlist", jarr);

		return sendJson.toJSONString();
	}

}

