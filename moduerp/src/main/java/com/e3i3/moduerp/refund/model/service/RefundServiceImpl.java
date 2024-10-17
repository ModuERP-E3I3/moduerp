package com.e3i3.moduerp.refund.model.service;

import java.util.List;

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

	@Override
	public List<RefundDTO> selectRefundApply() {
		return refundDAO.selectRefundApply();
	}

	@Override
	public List<RefundDTO> selectRefunding() {
		return refundDAO.selectRefunding();
	}

	@Override
	public List<RefundDTO> selectRefundComplete() {
		return refundDAO.selectRefundComplete();
	}

	@Override
	public RefundDTO selectRefundDetail(String refundId) {
		return refundDAO.selectRefundDetail(refundId);
	}

	@Override
	public void updateRefundStatus(String refundStatus, String refundId) {
		refundDAO.updateRefundStatus(refundStatus,refundId);
		
	}

	

	
}
