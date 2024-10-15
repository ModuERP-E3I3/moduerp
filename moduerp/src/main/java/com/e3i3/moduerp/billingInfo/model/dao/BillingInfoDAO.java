package com.e3i3.moduerp.billingInfo.model.dao;

import com.e3i3.moduerp.billingInfo.model.dto.BillingInfoDTO;

public interface BillingInfoDAO {

	void insertBillingInfo(BillingInfoDTO billingInfo);

	BillingInfoDTO selectBillingInfoData(String bizNumber);

}
