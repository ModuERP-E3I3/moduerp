package com.e3i3.moduerp.billingInfo.model.service;

import com.e3i3.moduerp.billingInfo.model.dto.BillingInfoDTO;

public interface BillingInfoService {

	void insertBillingInfo(BillingInfoDTO billingInfo);

	BillingInfoDTO selectBillingInfoData(String bizNumber);

}
