package com.e3i3.moduerp.employee.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.dto.EmployeeBasicInfo;

@Repository
public class EmployeeDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 직원 등록
	public void insertEmployee(Employee employee) {
		sqlSessionTemplate.insert("EmployeeMapper.insertEmployee", employee);
	}

	// 로그인 메소드
	public Employee selectEmployeeForLogin(Map<String, Object> params) {
		return sqlSessionTemplate.selectOne("EmployeeMapper.selectEmployeeForLogin", params);
	}

	// 직원 정보 업데이트
	public void updateEmployee(Employee employee) {
		sqlSessionTemplate.update("EmployeeMapper.updateEmployee", employee);
	}

	// uuid로 직원 삭제
	public void deleteEmployee(UUID uuid) {
		sqlSessionTemplate.delete("EmployeeMapper.deleteEmployee", uuid);
	}

	// uuid로 직원 조회
	public Employee selectEmployeeByUuid(UUID uuid) {
		return sqlSessionTemplate.selectOne("EmployeeMapper.selectEmployeeByUuid", uuid);
	}
	
	//사업자번호로 직원 조회
	public List<Employee> selectEmployeesByBizNum(String bizNumber) {
		return sqlSessionTemplate.selectList("EmployeeMapper.selectEmployeesByBizNum", bizNumber);
	}
	
	// 존재하는 모든 직원 조회(필요성 검토)
	public List<Employee> selectAllEmployees() {
		return sqlSessionTemplate.selectList("EmployeeMapper.selectAllEmployees");
	}

	public List<EmployeeBasicInfo> selectEmployeesByEmailAndBizNumber(String keyword, String bizNumber) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("keyword", keyword);
	    params.put("bizNumber", bizNumber);
	    return sqlSessionTemplate.selectList("EmployeeMapper.selectEmployeesByEmailAndBizNumber", params);
	}

	
	 // 이메일과 사업자번호로 직원 정보 조회
    public Employee selectEmployeeByEmailAndBizNumber(String empEmail, String bizNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("empEmail", empEmail);
        params.put("bizNumber", bizNumber);
        return sqlSessionTemplate.selectOne("EmployeeMapper.selectEmployeeByEmailAndBizNumber", params);
    }
}