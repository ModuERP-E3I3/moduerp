package com.e3i3.moduerp.pay.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3i3.moduerp.pay.model.dao.PayDAO;
import com.e3i3.moduerp.pay.model.dto.PayDTO;

@Service
public class PayServiceImpl implements PayService {

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

	@Override
	public String selectPaymentDate(String bizNumber) {
		return payDAO.selectPaymentDate(bizNumber);
	}

	@Override
	public String selectPurchasedModules(String bizNumber) {
		return payDAO.selectPurchasedModules(bizNumber);
	}

	@Override
	public int selectPayPrice(String bizNumber) {
		return payDAO.selectPayPrice(bizNumber);
	}

	@Override
	@Transactional
	public void updatePayPricePaymentItemByBizNumber(String combinedModules, int finalPrice, String bizNumber) {
		payDAO.updatePayPricePaymentItemByBizNumber(combinedModules, finalPrice, bizNumber);
	}

	@Override
	public String selectPayID(String bizNumber) {
		
		return payDAO.selectPayID(bizNumber);
	}

}
