package com.e3i3.moduerp.carres.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.carres.model.dto.CarresDto;

@Repository
public class CarresDaoImpl implements CarresDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "CarresMapper";
	
	@Override
	public List<CarresDto> getAllCarres(){
		return sqlSession.selectList(namespace + ".getAllCarres");
	}
	
	public CarresDto getCarresId(String carId) {
		return sqlSession.selectOne(namespace + ".getCarresId", carId);
	}
}
