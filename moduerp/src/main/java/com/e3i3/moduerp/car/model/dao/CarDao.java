package com.e3i3.moduerp.car.model.dao;

import java.util.List;


import com.e3i3.moduerp.car.model.dto.CarDto;

public interface CarDao{
	List<CarDto> getAllCar(String bizNumber);
	void insertCar(CarDto carDto);
	CarDto selectCarId(String carId);
	void updateCar(CarDto carDto);
	void deleteCar(CarDto carDto);
	List<CarDto> getCarByCarModel(String bizNumber, String filterText);
	List<CarDto> getCarByCarNum(String bizNumber, String filterText);
	List<CarDto> getCarByOwnershipStatus(String bizNumber, String filterText);
}
