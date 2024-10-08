package com.e3i3.moduerp.car.model.dao;

import java.util.List;


import com.e3i3.moduerp.car.model.dto.CarDto;

public interface CarDao{
	List<CarDto> getAllCar();
	void insertCar(CarDto carDto);
}
