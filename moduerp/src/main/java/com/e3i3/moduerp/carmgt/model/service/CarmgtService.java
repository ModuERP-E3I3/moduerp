package com.e3i3.moduerp.carmgt.model.service;

import java.util.List;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface CarmgtService {
	List<CarmgtDto> getAllCarmgt();
	void insertCarmgt(CarmgtDto carmgtDto);
	List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<CarmgtDto> getCarsByBizNumber(String bizNumber);
	List<Employee> getEmpNameDepart(String bizNumber);
	CarmgtDto getCarmgtListDetail(String paymentHistoryCode);
	void updateCarmgt(CarmgtDto carmgtDto);
	void deleteCarmgt(String paymentHistoryCode);
	
	/* void deleteCarmgt(String carId); */
}
