package com.e3i3.moduerp.employee.model.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.dto.EmployeeBasicInfo;

public interface EmployeeService {
	public void insertEmployee(Employee employee);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(UUID uuid);

	public Employee selectEmployeeByUuid(UUID uuid);

	public List<Employee> selectEmployeesByBizNum(String biznumber);

	public List<Employee> selectAllEmployees();

	public Employee validateLogin(Map<String, Object> params);

	public List<EmployeeBasicInfo> selectEmployeesByEmailAndBizNumber(String keyword, String bizNumber);
	
	//같은 직장 내에서의 같은 부서인 직원 조회
	public List<Employee> selectEmployeesByBizAndDepartment(String bizNumber, String departmentId);

	// 이메일과 사업자번호로 직원 조회
	public Employee selectEmployeeByEmailAndBizNumber(String empEmail, String bizNumber);

}