package com.e3i3.moduerp.employee.model.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.employee.model.dao.EmployeeDao;
import com.e3i3.moduerp.employee.model.dto.Employee;

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
	public void deleteEmployee(UUID uuid) {
		employeeDao.deleteEmployee(uuid);
	}

	// uuid로 직원 조회
	public Employee selectEmployeeByUuid(UUID uuid) {
		return selectEmployeeByUuid(uuid);
	}

	// 전체 직원 조회
	public List<Employee> selectAllEmployees() {
		return employeeDao.selectAllEmployees();
	}

	// 사업자번호로 직원 조회
	// TODO: 사업자번호로 직원 조회하는 컨트롤러 메소드 만들기!!
	@Override
	public List<Employee> selectEmployeesByBizNum(String biznumber) {
		return employeeDao.selectEmployeesByBizNum(biznumber);
	}
	
	// 로그인 검증 메소드
	@Override
	public Employee validateLogin(Map<String, Object> params) {
		// 전달된 파라미터(사업자번호, 승인코드, 이메일)을 이용해 직원 조회
		Employee employee = employeeDao.selectEmployeeForLogin(params);
		if (employee != null) {
			// 암호화된 비밀번호 비교
			String inputPassword=(String)params.get("password"); // 사용자가 입력한 비밀번호
			String storedPassword=employee.getPassword(); //데이터베이스에 저장된 암호화된 비밀번호
			
			// 입력된 비밀번호와 데이터베이스의 암호화된 비밀번호 비교
			if(bcryptPasswordEncoder.matches(inputPassword, storedPassword)) {
				return employee; //비밀번호가 일치하면 해당 직원 반환
			}
		}
		return null; //일치하지 않으면 null 반환
	}

}