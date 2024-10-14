package com.e3i3.moduerp.carmgt.model.service;

import java.util.List;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface CarmgtService {
	List<CarmgtDto> getAllCarmgt(String bizNumber);
	void insertCarmgt(CarmgtDto carmgtDto);
	List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<CarmgtDto> getCarsByBizNumber(String bizNumber);
	List<Employee> getEmpNameDepart(String bizNumber);
	CarmgtDto getCarmgtListDetail(String paymentHistoryCode);
	void updateCarmgt(CarmgtDto carmgtDto);
	void deleteCarmgt(String paymentHistoryCode);
	List<CarmgtDto> getCarByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate);
	List<CarmgtDto> getCarByFilter(String bizNumber, String option, String filterText);
	List<CarmgtDto> getCarByFilterOnlyDate(String bizNumber, String startDate, String endDate);
	List<CarmgtDto> getCarByFilterStartDate(String bizNumber, String startDate);
	List<CarmgtDto> getCarByFilterEndDate(String bizNumber, String endDate);
	
	/* void deleteCarmgt(String carId); */
}
