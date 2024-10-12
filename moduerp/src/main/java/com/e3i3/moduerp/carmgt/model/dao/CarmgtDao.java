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
}
