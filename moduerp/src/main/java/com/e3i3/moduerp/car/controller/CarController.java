package com.e3i3.moduerp.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class CarController {
    
    // carRes.jsp 
    @CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 특정 도메인만 허용
    @RequestMapping(value = "/carRes.do", method = RequestMethod.GET)
    public String forwardCarRes() {
        return "car/carRes";  
    }
    
    // carMgt.jsp
    @CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 특정 도메인만 허용
    @RequestMapping(value = "/carMgt.do", method = RequestMethod.GET)
    public String forwardCarMgt() {
        return "car/carMgt"; 
    }
    
    // map.jsp
    @CrossOrigin(origins = "https://apis-navi.kakaomobility.com/v1/directions") // 특정 도메인만 허용
    @RequestMapping(value = "/map.do", method = RequestMethod.GET)
    public String forwardMap() {
        return "car/map"; 
    }
}
