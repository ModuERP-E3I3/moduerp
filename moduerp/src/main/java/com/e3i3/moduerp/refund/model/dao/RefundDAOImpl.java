package com.e3i3.moduerp.refund.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<RefundDTO> selectRefundApply() {
		return sqlSession.selectList(namespace + ".selectRefundApply");
	}

	@Override
	public List<RefundDTO> selectRefunding() {
		return sqlSession.selectList(namespace + ".selectRefunding");
	}

	@Override
	public List<RefundDTO> selectRefundComplete() {
		return sqlSession.selectList(namespace + ".selectRefundComplete");
	}

	@Override
	public RefundDTO selectRefundDetail(String refundId) {
		return sqlSession.selectOne(namespace + ".selectRefundDetail", refundId);
	}

	@Override
	public void updateRefundStatus(String refundStatus, String refundId) {
		Map<String, Object> params = new HashMap<>();
		params.put("refundStatus", refundStatus);
		params.put("refundId", refundId);
		sqlSession.update(namespace + ".updateRefundStatus", params);

	}

}
