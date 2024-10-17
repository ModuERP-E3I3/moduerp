package com.e3i3.moduerp.paylog.model.service;

import java.util.List;

import com.e3i3.moduerp.paylog.model.dto.PayLogDTO;

public interface PayLogService {

	void insertPayLog(PayLogDTO payLogDTO);

	List<PayLogDTO> selectPayLogByBizNumber(String bizNumber);

	PayLogDTO selectPayLogByLogId(String logId);

	void updateRefund(String payLogId);

	void updateRefundStatus(String logId);

	void updateRefundStatus(String refundStatus, String bizNumber);

}
