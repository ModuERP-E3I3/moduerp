package com.e3i3.moduerp.paylog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.paylog.model.dao.PayLogDAO;
import com.e3i3.moduerp.paylog.model.dto.PayLogDTO;

@Service
public class PayLogServiceImpl implements PayLogService {

	@Autowired
	private PayLogDAO payLogDAO;

	@Override
	public void insertPayLog(PayLogDTO payLogDTO) {
		payLogDAO.insertPayLog(payLogDTO);

	}

	@Override
	public List<PayLogDTO> selectPayLogByBizNumber(String bizNumber) {
		
		return payLogDAO.selectPayLogByBizNumber(bizNumber);
	}

	@Override
	public PayLogDTO selectPayLogByLogId(String logId) {
		return payLogDAO.selectPayLogByLogId(logId);
	}

	@Override
	public void updateRefund(String payLogId) {
		payLogDAO.updateRefund(payLogId);
		
	}

	@Override
	public void updateRefundStatus(String logId) {
		payLogDAO.updateRefundStatus(logId);
		
	}

	@Override
	public void updateRefundStatus(String refundStatus, String bizNumber) {
		payLogDAO.updateRefundStatus(refundStatus,bizNumber);
		
	}

}
