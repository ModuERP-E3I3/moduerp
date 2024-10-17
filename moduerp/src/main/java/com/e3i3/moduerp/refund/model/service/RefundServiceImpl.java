package com.e3i3.moduerp.refund.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.refund.model.dao.RefundDAO;
import com.e3i3.moduerp.refund.model.dto.RefundDTO;

@Service
public class RefundServiceImpl implements RefundService{
	@Autowired
	private RefundDAO refundDAO;

	@Override
	public void insertRefund(RefundDTO refundDTO) {
		refundDAO.insertRefund(refundDTO);
		
	}

	@Override
	public void deleteRefundByOrderId(String orderId) {
		refundDAO.deleteRefundByOrderId(orderId);
		
	}

	
}
