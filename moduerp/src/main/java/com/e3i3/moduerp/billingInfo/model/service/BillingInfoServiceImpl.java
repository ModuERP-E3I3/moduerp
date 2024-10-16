package com.e3i3.moduerp.billingInfo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.billingInfo.model.dao.BillingInfoDAO;
import com.e3i3.moduerp.billingInfo.model.dto.BillingInfoDTO;

@Service
public class BillingInfoServiceImpl implements BillingInfoService {

	@Autowired
	private BillingInfoDAO billingInfoDAO;

	@Override
	public void insertBillingInfo(BillingInfoDTO billingInfo) {

		billingInfoDAO.insertBillingInfo(billingInfo);
	}

	@Override
	public BillingInfoDTO selectBillingInfoData(String bizNumber) {
		return billingInfoDAO.selectBillingInfoData(bizNumber);
	}

	@Override
	public List<BillingInfoDTO> getBillingInfoByBizNumbers(List<String> bizNumbers) {
		return billingInfoDAO.getBillingInfoByBizNumbers(bizNumbers);
	}

	@Override
	public BillingInfoDTO selectBillingInfoByBizNumber(String bizNumber) {
		return billingInfoDAO.selectBillingInfoByBizNumber(bizNumber);
	}

	@Override
	public void deleteBillingInfoByCardBillingId(String cardBillingId) {
		billingInfoDAO.deleteBillingInfoByCardBillingId(cardBillingId);
		
	}
}
