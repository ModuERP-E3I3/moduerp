package com.e3i3.moduerp.carres.model.service;

import java.sql.Timestamp;
import java.util.List;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.carres.model.dto.CarresDto;

public interface CarresService {

	List<CarresDto> getAllCarres(String bizNumber);

	List<Employee> getEmpNameDepart(String bizNumber);

	List<CarresDto> getCarsbyBizNumber(String bizNumber);

	void insertCarres(CarresDto carresDto);

	CarresDto getCarresListDetail(String carReserveCode);
	
	void updateCarres(CarresDto carresDto);

	void deleteCarres(String carReserveCode);

	List<CarresDto> getCarByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate);

	List<CarresDto> getCarByFilter(String bizNumber, String option, String filterText);

	List<CarresDto> getCarByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<CarresDto> getCarByFilterStartDate(String bizNumber, String startDate);

	List<CarresDto> getCarByFilterEndDate(String bizNumber, String endDate);

	List<CarresDto> getOverlappingReservations(String carModel, String carNum, Timestamp reserveStartDate,
			Timestamp reserveEndDate, String bizNumber);

	

	

}
