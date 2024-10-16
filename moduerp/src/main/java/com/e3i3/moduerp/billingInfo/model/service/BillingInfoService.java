package com.e3i3.moduerp.billingInfo.model.service;

import java.util.List;

import com.e3i3.moduerp.billingInfo.model.dto.BillingInfoDTO;

public interface BillingInfoService {

	void insertBillingInfo(BillingInfoDTO billingInfo);

	BillingInfoDTO selectBillingInfoData(String bizNumber);

	List<BillingInfoDTO> getBillingInfoByBizNumbers(List<String> bizNumbers);

	BillingInfoDTO selectBillingInfoByBizNumber(String bizNumber);

	void deleteBillingInfoByCardBillingId(String cardBillingId);

}
