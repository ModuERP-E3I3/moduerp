package com.e3i3.moduerp.paylog.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.paylog.model.dto.PayLogDTO;

@Repository
public class PayLogDAOImpl implements PayLogDAO {
	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "PayLogMapper";

	@Override
	public void insertPayLog(PayLogDTO payLogDTO) {
		sqlSession.insert(namespace + ".insertPayLog", payLogDTO);

	}

	@Override
	public List<PayLogDTO> selectPayLogByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".selectPayLogByBizNumber", bizNumber);
	}

	@Override
	public PayLogDTO selectPayLogByLogId(String logId) {
		return sqlSession.selectOne(namespace + ".selectPayLogByLogId", logId);
	}

	@Override
	public void updateRefund(String payLogId) {
		sqlSession.update(namespace + ".updateRefund", payLogId);

	}

	@Override
	public void updateRefundStatus(String logId) {
		sqlSession.update(namespace + ".updateRefundStatus", logId);

	}

	@Override
	public void updateRefundStatus(String refundStatus, String orderId) {
		Map<String, Object> params = new HashMap<>();
		params.put("refundStatus", refundStatus);
		params.put("orderId", orderId);
		sqlSession.update(namespace + ".updateRefundStatusByBizNumber", params);
	}

}
