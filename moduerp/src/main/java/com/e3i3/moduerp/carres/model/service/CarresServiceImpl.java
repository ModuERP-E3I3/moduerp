package com.e3i3.moduerp.carres.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.carres.model.dao.CarresDao;
import com.e3i3.moduerp.carres.model.dto.CarresDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Service
public class CarresServiceImpl implements CarresService{
	@Autowired
	private CarresDao carresDao;
	
	@Override
	public List<CarresDto> getAllCarres(){
		return carresDao.getAllCarres();
	}

	@Override
	public List<Employee> getEmpNameDepart(String bizNumber) {
		return carresDao.getEmpNameDepart(bizNumber);
	}

	@Override
	public List<CarresDto> getCarsbyBizNumber(String bizNumber) {
		return carresDao.getCarsByBizNumber(bizNumber);
	}

	@Override
	public void insertCarres(CarresDto carresDto) {
		carresDao.insertCarres(carresDto);
	}

	@Override
	public CarresDto getCarresListDetail(String carReserveCode) {
		return carresDao.selectcarReserveCode(carReserveCode);
	}

	@Override
	public void updateCarres(CarresDto carresDto) {
		carresDao.updateCarres(carresDto);
		
	}

	@Override
	public void deleteCarres(String carReserveCode) {
		carresDao.deleteCarres(carReserveCode);
		
	}
}
