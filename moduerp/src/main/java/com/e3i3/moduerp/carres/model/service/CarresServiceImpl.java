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

	@Override
	public List<CarresDto> getAllCarres(String bizNumber) {
		// TODO Auto-generated method stub
		return carresDao.getAllCarres(bizNumber);
	}

	@Override
	public List<CarresDto> getCarByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate) {
		if (option.equals("carModel")) {
			return carresDao.getCarByCarModelDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("carNum")) {
			return carresDao.getCarByCarNumDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("empName")) {
			return carresDao.getCarByEmpNameDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("departmentId")) {
			return carresDao.getCarByDepartmentIdDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("drivingStatus")) {
			return carresDao.getCarByDrivingStatusDate(bizNumber, filterText, startDate, endDate);
		}
		return null;
	}

	@Override
	public List<CarresDto> getCarByFilter(String bizNumber, String option, String filterText) {
		if (option.equals("carModel")) {
			return carresDao.getCarByCarModel(bizNumber, filterText);
		} else if (option.equals("carNum")) {
			return carresDao.getCarByCarNum(bizNumber, filterText);
		} else if (option.equals("empName")) {
			return carresDao.getCarByEmpName(bizNumber, filterText);
		} else if (option.equals("departmentId")) {
			return carresDao.getCarByDepartmentId(bizNumber, filterText);
		} else if (option.equals("drivingStatus")) {
			return carresDao.getCarByDrivingStatus(bizNumber, filterText);
		}
		return null;
	}
}
