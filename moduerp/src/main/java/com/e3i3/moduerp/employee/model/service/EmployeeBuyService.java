package com.e3i3.moduerp.employee.model.service;

import java.util.List;

import com.e3i3.moduerp.delivery.model.dto.DeliveryDTO;

public interface EmployeeBuyService {

	String getEmployeeNameByUuid(String uuid);

	List<String> getEmployeeNamesByBizNumber(String bizNumber);

}
