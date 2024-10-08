package com.e3i3.moduerp.car.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.car.model.dao.CarDao;
import com.e3i3.moduerp.car.model.dto.CarDto;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarDao carDao;
	
	@Override
	public List<CarDto> getAllCar(){
		return carDao.getAllCar();
	}
	
	@Override
    public void insertCar(CarDto carDto) {
        carDao.insertCar(carDto);  // DAO 호출
    }
	
	@Override
	public CarDto getCarListDetail(String carId) {
		return carDao.selectCarId(carId);
	}
}
