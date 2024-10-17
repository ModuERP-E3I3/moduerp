package com.e3i3.moduerp.refund.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.refund.model.dto.RefundDTO;

@Repository
public class RefundDAOImpl implements RefundDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "RefundMapper";

	@Override
	public void insertRefund(RefundDTO refundDTO) {
		sqlSession.insert(namespace + ".insertRefund", refundDTO);
		
	}

	@Override
	public void deleteRefundByOrderId(String orderId) {
		 sqlSession.delete(namespace + ".deleteRefundByOrderId", orderId);
		
	}
}
