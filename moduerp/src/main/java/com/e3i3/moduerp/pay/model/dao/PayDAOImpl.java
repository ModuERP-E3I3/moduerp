package com.e3i3.moduerp.pay.model.dao;

import java.util.List;

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

}
