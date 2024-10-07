package com.e3i3.moduerp.employee.model.dao;

import java.util.List;

public interface EmployeeBuyDAO {
	List<String> selectEmployeeNamesByBizNumber(String bizNumber);

	String selectEmployeeNameByUuid(String uuid);
}
