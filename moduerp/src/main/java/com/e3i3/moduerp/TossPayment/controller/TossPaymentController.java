package com.e3i3.moduerp.TossPayment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TossPaymentController {

	@RequestMapping("/payment/reqular")
	public ModelAndView showReqularPaymentPage() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("reqular_payment/reqular_payment");
	    return mav;
	}

}
