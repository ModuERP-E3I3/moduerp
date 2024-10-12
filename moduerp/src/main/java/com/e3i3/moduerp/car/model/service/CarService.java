package com.e3i3.moduerp.car.model.service;

import java.util.List;

import com.e3i3.moduerp.car.model.dto.CarDto;

public interface CarService{
	List<CarDto> getAllCar(String bizNumber);
	void insertCar(CarDto carDto);
	CarDto getCarListDetail(String carId);
	void updateCar(CarDto carDto);
	void deleteCar(CarDto carDto);
	List<CarDto> getCarByFilter(String bizNumber, String option, String filterText);
}
    
	
//    @Autowired
//    private SqlSessionTemplate sqlSessionTemplate;
//
//    public ArrayList<Car> selectCarList() {
//    	List<Car> list = sqlSessionTemplate.selectList("carMapper.selectCar");
//    	return (ArrayList<Car>)list;
 
