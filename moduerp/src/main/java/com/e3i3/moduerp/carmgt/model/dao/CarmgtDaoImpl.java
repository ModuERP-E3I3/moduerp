package com.e3i3.moduerp.carmgt.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;

@Repository
public class CarmgtDaoImpl implements CarmgtDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "CarmgtMapper";
	
	@Override
	public List<CarmgtDto> getAllCarmgt(){
		return sqlSession.selectList(namespace +  ".getAllCarmgt");
	}
	
	public CarmgtDto getCarmgtId(String carmgtId) {
		return sqlSession.selectOne(namespace + ".getCarmgtId", carmgtId);
	}

	/*
	 * @Override public void deleteCarmgt(String carId) {
	 * sqlSession.delete(namespace + ".deleteCarmgt", carId);
	 * 
	 * }
	 */
}
