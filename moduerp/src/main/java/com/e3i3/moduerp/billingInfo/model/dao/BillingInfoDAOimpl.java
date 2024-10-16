package com.e3i3.moduerp.billingInfo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.billingInfo.model.dto.BillingInfoDTO;

@Repository
public class BillingInfoDAOimpl implements BillingInfoDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String namespace = "BillingInfoMapper";

	@Override
	public void insertBillingInfo(BillingInfoDTO billingInfo) {
		sqlSession.insert(namespace + ".insertBillingInfo", billingInfo);
	}

	@Override
	public BillingInfoDTO selectBillingInfoData(String bizNumber) {
		return sqlSession.selectOne(namespace + ".selectBillingInfoData", bizNumber);
	}

	@Override
	public List<BillingInfoDTO> getBillingInfoByBizNumbers(List<String> bizNumbers) {
		return sqlSession.selectList(namespace + ".selectBillingInfoByBizNumbers", bizNumbers);
	}

	@Override
	public BillingInfoDTO selectBillingInfoByBizNumber(String bizNumber) {
		return sqlSession.selectOne(namespace + ".selectBillingInfoByBizNumber", bizNumber);
	}

	@Override
	public void deleteBillingInfoByCardBillingId(String cardBillingId) {
		sqlSession.delete(namespace + ".deleteBillingInfoByCardBillingId" , cardBillingId);
		
	}
}
