package com.e3i3.moduerp.carres.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.carres.model.dto.CarresDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

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

	@Override
	public List<Employee> getEmpNameDepart(String bizNumber) {
		return sqlSession.selectList(namespace + ".getEmpNameDepart" , bizNumber);
	}

	@Override
	public List<CarresDto> getCarsByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".getCarsByBizNumber", bizNumber);
	}

	@Override
	public void insertCarres(CarresDto carresDto) {
		sqlSession.insert(namespace + ".insertCarres", carresDto);
	}

	@Override
	public CarresDto selectcarReserveCode(String carReserveCode) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".selectcarReserveCode", carReserveCode);
	}
}
