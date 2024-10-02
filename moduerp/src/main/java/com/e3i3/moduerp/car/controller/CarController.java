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
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // Ư�� �����θ� ���
	@RequestMapping(value = "/carRes.do", method = RequestMethod.GET)
	public String forwardCarRes() {
		return "car/carRes";
	}

	// carMgt.jsp view
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // Ư�� �����θ� ���
	@RequestMapping(value = "/carMgt.do", method = RequestMethod.GET)
	public String forwardCarMgt() {
		return "car/carMgt";
	}

	// map.jsp view
	@CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // Ư�� �����θ� ���
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
			JSONObject job = new JSONObject(); // org.json.simple.JSONObject ����Ʈ��

			job.put("carId", URLEncoder.encode(car.getCarId(), "utf-8"));
			// ���ڿ����� �ѱ��� ���ԵǾ� �ִٸ�, �ݵ�� ���ڵ��ؼ� �����ؾ� ��
			// java.net.URLEncoder �� static �޼ҵ��� encode('�ѱ����ִ¹��ڿ���', '���ڼ�') �����
			job.put("carModel", URLEncoder.encode(car.getCarModel(), "utf-8"));
			// ��¥�����ʹ� �ݵ�� ���ڿ��� �ٲ㼭 ������ �� : ��¥ �״�� �����ϸ� �信�� json ��ü ��� �� ��
			job.put("ownershipStatus", URLEncoder.encode(car.getOwnershipStatus(), "utf-8"));

			jarr.add(job); // �迭�� �߰�
		}
		// ���ۿ� json ��ü ������
		JSONObject sendJson = new JSONObject();
		// ���ۿ� json �� jarr �� ������
		sendJson.put("nlist", jarr);

		return sendJson.toJSONString();
	}

}

