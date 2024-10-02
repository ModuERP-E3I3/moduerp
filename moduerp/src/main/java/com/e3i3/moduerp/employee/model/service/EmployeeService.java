package com.e3i3.moduerp.employee.model.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.e3i3.moduerp.employee.model.dto.Employee;

public interface EmployeeService {
	public void insertEmployee(Employee employee);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(UUID uuid);

	public Employee selectEmployeeByUuid(UUID uuid);
	
	public List<Employee> selectEmployeesByBizNum(String biznumber);

	public List<Employee> selectAllEmployees();

	public Employee validateLogin(Map<String, Object> params);
}