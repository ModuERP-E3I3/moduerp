package com.e3i3.moduerp.carres.model.dao;

import java.util.List;

import com.e3i3.moduerp.carres.model.dto.CarresDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface CarresDao {
	List<CarresDto> getAllCarres();

	List<Employee> getEmpNameDepart(String bizNumber);

	List<CarresDto> getCarsByBizNumber(String bizNumber);

	void insertCarres(CarresDto carresDto);

}
