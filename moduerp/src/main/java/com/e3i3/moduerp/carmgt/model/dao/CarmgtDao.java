package com.e3i3.moduerp.carmgt.model.dao;

import java.util.List;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface CarmgtDao {
	List<CarmgtDto> getAllCarmgt(String bizNumber);
	void insertCarmgt(CarmgtDto carmgtDto);
	/* void deleteCarmgt(String carId); */
	
	List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<CarmgtDto> getCarsByBizNumber(String bizNumber);
    
	List<Employee> getEmpNameDepart(String bizNumber);
	CarmgtDto selectpaymentHistoryCode(String paymentHistoryCode);
	void updateCarmgt(CarmgtDto carmgtDto);
	void deleteCarmgt(String paymentHistoryCode);
	List<CarmgtDto> getCarByCarModelDate(String bizNumber, String filterText, String startDate, String endDate);
	List<CarmgtDto> getCarByCarNumDate(String bizNumber, String filterText, String startDate, String endDate);
	List<CarmgtDto> getCarByEmpNameDate(String bizNumber, String filterText, String startDate, String endDate);
	List<CarmgtDto> getCarByDepartmentIdDate(String bizNumber, String filterText, String startDate, String endDate);
	List<CarmgtDto> getCarByCarModel(String bizNumber, String filterText);
	List<CarmgtDto> getCarByCarNum(String bizNumber, String filterText);
	List<CarmgtDto> getCarByEmpName(String bizNumber, String filterText);
	List<CarmgtDto> getCarByDepartmentId(String bizNumber, String filterText);
	List<CarmgtDto> getCarByFilterOnlyDate(String bizNumber, String startDate, String endDate);
	List<CarmgtDto> getCarByFilterStartDate(String bizNumber, String startDate);
	List<CarmgtDto> getCarByFilterEndDate(String bizNumber, String endDate);
}
