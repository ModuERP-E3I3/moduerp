package com.e3i3.moduerp.employee.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeBuyDAOImpl implements EmployeeBuyDAO{

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "EmployeeBuyMapper";
	
	@Override
	public List<String> selectEmployeeNamesByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".selectEmployeeNamesByBizNumber", bizNumber);
	}

	@Override
	public String selectEmployeeNameByUuid(String uuid) {
		return sqlSession.selectOne(namespace + ".selectEmployeeNameByUuid", uuid);
	}

}
