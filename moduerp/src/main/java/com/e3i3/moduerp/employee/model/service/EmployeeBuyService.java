package com.e3i3.moduerp.employee.model.service;

import java.util.List;

public interface EmployeeBuyService {

	List<String> getEmployeeNamesByBizNumber(String bizNumber);
	
	String getEmployeeNameByUuid(String uuid);

}
