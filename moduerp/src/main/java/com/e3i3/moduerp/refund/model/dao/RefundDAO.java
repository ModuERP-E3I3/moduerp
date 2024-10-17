package com.e3i3.moduerp.refund.model.dao;

import com.e3i3.moduerp.refund.model.dto.RefundDTO;

public interface RefundDAO {

	void insertRefund(RefundDTO refundDTO);

	void deleteRefundByOrderId(String orderId);

}
