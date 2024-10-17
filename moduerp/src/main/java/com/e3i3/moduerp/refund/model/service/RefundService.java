package com.e3i3.moduerp.refund.model.service;

import com.e3i3.moduerp.refund.model.dto.RefundDTO;

public interface RefundService {

	void insertRefund(RefundDTO refundDTO);

	void deleteRefundByOrderId(String orderId);

	

}
