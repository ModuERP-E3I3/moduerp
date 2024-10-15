package com.e3i3.moduerp.pay.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.pay.model.dao.PayDAO;
import com.e3i3.moduerp.pay.model.dto.PayDTO;

@Service
public class PayServiceImpl implements PayService{

	@Autowired
	private PayDAO payDAO;
	
	@Override
	public void insertPay(PayDTO payDTO) {
		payDAO.insertPay(payDTO);
		
	}

	@Override
	public List<PayDTO> getPaymentsByRequestDay(String today) {
		return payDAO.getPaymentsByRequestDay(today);
	}

	

}
