package com.e3i3.moduerp.carres.model.dao;

import java.util.List;

import com.e3i3.moduerp.carres.model.dto.CarresDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface CarresDao {
	List<CarresDto> getAllCarres(String bizNumber);

	List<Employee> getEmpNameDepart(String bizNumber);

	List<CarresDto> getCarsByBizNumber(String bizNumber);

	void insertCarres(CarresDto carresDto);

	CarresDto selectcarReserveCode(String carReserveCode);

	void updateCarres(CarresDto carresDto);

	void deleteCarres(String carReserveCode);

	List<CarresDto> getCarByCarModelDate(String bizNumber, String filterText, String startDate, String endDate);

	List<CarresDto> getCarByCarNumDate(String bizNumber, String filterText, String startDate, String endDate);

	List<CarresDto> getCarByEmpNameDate(String bizNumber, String filterText, String startDate, String endDate);

	List<CarresDto> getCarByDepartmentIdDate(String bizNumber, String filterText, String startDate,
			String endDate);

	List<CarresDto> getCarByDrivingStatusDate(String bizNumber, String filterText, String startDate,
			String endDate);

	List<CarresDto> getCarByCarModel(String bizNumber, String filterText);

	List<CarresDto> getCarByCarNum(String bizNumber, String filterText);

	List<CarresDto> getCarByEmpName(String bizNumber, String filterText);

	List<CarresDto> getCarByDepartmentId(String bizNumber, String filterText);

	List<CarresDto> getCarByDrivingStatus(String bizNumber, String filterText);

	List<CarresDto> getCarByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<CarresDto> getCarByFilterStartDate(String bizNumber, String startDate);

	List<CarresDto> getCarByFilterEndDate(String bizNumber, String endDate);

}
