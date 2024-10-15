package com.e3i3.moduerp.pay.model.service;

import java.util.List;

import com.e3i3.moduerp.pay.model.dto.PayDTO;

public interface PayService {

	void insertPay(PayDTO payDTO);

	List<PayDTO> getPaymentsByRequestDay(String today);

	

}
