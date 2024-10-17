package com.e3i3.moduerp.refund.model.service;

import java.util.List;

import com.e3i3.moduerp.refund.model.dto.RefundDTO;

public interface RefundService {

	void insertRefund(RefundDTO refundDTO);

	void deleteRefundByOrderId(String orderId);

	List<RefundDTO> selectRefundApply();

	List<RefundDTO> selectRefunding();

	List<RefundDTO> selectRefundComplete();

	RefundDTO selectRefundDetail(String refundId);

	void updateRefundStatus(String refundStatus, String refundId);

	

}
