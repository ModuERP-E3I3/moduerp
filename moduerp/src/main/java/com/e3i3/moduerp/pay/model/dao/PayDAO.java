package com.e3i3.moduerp.pay.model.dao;

import java.util.List;

import com.e3i3.moduerp.pay.model.dto.PayDTO;

public interface PayDAO {

	void insertPay(PayDTO payDTO);

	List<PayDTO> getPaymentsByRequestDay(String today);

	String selectPaymentDate(String bizNumber);

	String selectPurchasedModules(String bizNumber);

	int selectPayPrice(String bizNumber);

	void updatePayPricePaymentItemByBizNumber(String combinedModules, int finalPrice, String bizNumber);

	String selectPayID(String bizNumber);

	

}
