package com.e3i3.moduerp.car.model.dao;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<CarDto> getCarByCarModel(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace+".selectCarByCarModel",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarDto> getCarByCarNum(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace+".selectCarByCarNum",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarDto> getCarByOwnershipStatus(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace+".selectCarByOwnershipStatus",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}
	
}
