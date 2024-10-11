package com.e3i3.moduerp.car.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.car.model.dto.CarDto;

@Repository
public class CarDaoImpl implements CarDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "CarMapper";
	
	@Override
	public List<CarDto> getAllCar(String bizNumber) {
		return sqlSession.selectList(namespace + ".getAllCar", bizNumber);
	}
	
	@Override
    public void insertCar(CarDto carDto) {
        sqlSession.insert(namespace + ".insertCar", carDto);  // MyBatis 호출
    }
	
	@Override
	public CarDto selectCarId(String carId) {
		return sqlSession.selectOne(namespace + ".selectCarId", carId);
	}
	
	@Override
	public void updateCar(CarDto carDto) {
		sqlSession.update(namespace + ".updateCar", carDto);
	}

	@Override
	public void deleteCar(CarDto carDto) {
		sqlSession.update(namespace + ".deleteCar", carDto);
	}
	
}
