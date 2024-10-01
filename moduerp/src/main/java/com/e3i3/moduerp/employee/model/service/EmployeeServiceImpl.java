package com.e3i3.moduerp.employee.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.employee.model.dao.EmployeeDao;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	// 직원 등록
	public void insertEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}

	// 직원 수정
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}

	// 직원 삭제
	public void deleteEmployee(UUID uuid) {
		employeeDao.deleteEmployee(uuid);
	}

	// 직원 조회
	public Employee selectEmployeeByUuid(UUID uuid) {
		return selectEmployeeByUuid(uuid);
	}

	// 직원 회사 조회
	public List<Employee> selectAllEmployees() {
		return employeeDao.selectAllEmployees();
	}
}
