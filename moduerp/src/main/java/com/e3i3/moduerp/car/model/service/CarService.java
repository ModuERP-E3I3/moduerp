package com.e3i3.moduerp.car.model.service;

import java.util.List;

import com.e3i3.moduerp.car.model.dto.CarDto;

public interface CarService{
	List<CarDto> getAllCar();
	void insertCar(CarDto carDto);
	CarDto getCarListDetail(String carId);
	void updateCar(CarDto carDto);
}
    
	
//    @Autowired
//    private SqlSessionTemplate sqlSessionTemplate;
//
//    public ArrayList<Car> selectCarList() {
//    	List<Car> list = sqlSessionTemplate.selectList("carMapper.selectCar");
//    	return (ArrayList<Car>)list;
 
