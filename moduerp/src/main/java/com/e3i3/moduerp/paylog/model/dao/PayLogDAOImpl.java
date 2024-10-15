package com.e3i3.moduerp.paylog.model.dao;

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

}
