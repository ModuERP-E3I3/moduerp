package com.e3i3.moduerp.pay.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.pay.model.dto.PayDTO;

@Repository
public class PayDAOImpl implements PayDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "PayMapper";

	@Override
	public void insertPay(PayDTO payDTO) {
		sqlSession.insert(namespace + ".insertPay", payDTO);

	}

	@Override
	public List<PayDTO> getPaymentsByRequestDay(String today) {
		return sqlSession.selectList(namespace + ".getPaymentsByRequestDay" , today);
	}

	@Override
	public String selectPaymentDate(String bizNumber) {
		return sqlSession.selectOne(namespace + ".selectPaymentDate" , bizNumber);
	}

	@Override
	public String selectPurchasedModules(String bizNumber) {
		return sqlSession.selectOne(namespace + ".selectPurchasedModules" , bizNumber);
	}

	@Override
	public int selectPayPrice(String bizNumber) {
		return sqlSession.selectOne(namespace + ".selectPayPrice" , bizNumber);
	}
	
	@Override
    public void updatePayPricePaymentItemByBizNumber(String combinedModules, int finalPrice, String bizNumber) {
        // Map을 사용해 파라미터 전달
        Map<String, Object> params = new HashMap<>();
        params.put("combinedModules", combinedModules);
        params.put("finalPrice", finalPrice);
        params.put("bizNumber", bizNumber);

        sqlSession.update(namespace + ".updatePayPricePaymentItemByBizNumber", params);
    }

	@Override
	public String selectPayID(String bizNumber) {
		 return sqlSession.selectOne(namespace + ".selectPayID", bizNumber);
		
	}
}
