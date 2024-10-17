package com.e3i3.moduerp.employee.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.employee.model.dao.EmployeeDao;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.dto.EmployeeBasicInfo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	// 직원 등록
	public void insertEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}

	// 직원 수정
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}

	// 직원 삭제
	public void deleteEmployee(String uuid) {
		employeeDao.deleteEmployee(uuid);
	}

	// uuid로 직원 조회
	public Employee selectEmployeeByUuid(String uuid) {
		return employeeDao.selectEmployeeByUuid(uuid);
	}

	// 전체 직원 조회
	public List<Employee> selectAllEmployees() {
		return employeeDao.selectAllEmployees();
	}

	@Override
	public List<EmployeeBasicInfo> selectEmployeesByEmailAndBizNumber(String keyword, String bizNumber) {
		return employeeDao.selectEmployeesByEmailAndBizNumber(keyword, bizNumber);
	}

	// 사업자번호로 직원 조회
	@Override
	public List<Employee> selectEmployeesByBizNum(String biznumber) {
		return employeeDao.selectEmployeesByBizNum(biznumber);
	}

	@Override
	public Employee selectEmployeeByEmailAndBizNumber(String empEmail, String bizNumber) {
		return employeeDao.selectEmployeeByEmailAndBizNumber(empEmail, bizNumber);
	}

	// 로그인 검증 메소드
	@Override
	public Employee validateLogin(Map<String, Object> params) {
		// 전달된 파라미터(사업자번호, 승인코드, 이메일)을 이용해 직원 조회
		Employee employee = employeeDao.selectEmployeeForLogin(params);
		if (employee != null) {
			// 암호화된 비밀번호 비교
			String inputPassword = (String) params.get("password"); // 사용자가 입력한 비밀번호
			String storedPassword = employee.getPassword(); // 데이터베이스에 저장된 암호화된 비밀번호

			// 입력된 비밀번호와 데이터베이스의 암호화된 비밀번호 비교
			if (bcryptPasswordEncoder.matches(inputPassword, storedPassword)) {
				return employee; // 비밀번호가 일치하면 해당 직원 반환
			}
		}
		return null; // 일치하지 않으면 null 반환
	}

	@Override
	public List<Employee> selectEmployeesByBizAndDepartment(String bizNumber, String departmentId) {
		return employeeDao.selectEmployeesByBizAndDepartment(bizNumber, departmentId);
	}

	@Override
	public int updatePassword(String uuid, String password) {
		return employeeDao.updatePassword(uuid, password);
	}
	
    @Override
    public int updateUserPhoneByUuid(String uuid, String userPhone) {
        return employeeDao.updateUserPhoneByUuid(uuid, userPhone);
    }

	@Override
	public int updateAddressByUuid(String uuid, String address) {
		return employeeDao.updateAddressByUuid(uuid, address);
	}
}