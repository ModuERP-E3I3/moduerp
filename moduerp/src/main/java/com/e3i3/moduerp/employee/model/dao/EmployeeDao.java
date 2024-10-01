package com.e3i3.moduerp.employee.model.dao;

import java.util.List;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.employee.model.dto.Employee;

@Repository
public class EmployeeDao {
	// root-context.xml에 설정된 마이바티스 객체를 연결 사용함
		@Autowired
		private SqlSessionTemplate sqlSessionTemplate;

		// 직원 등록
		public void insertEmployee(Employee employee) {
			sqlSessionTemplate.insert("EmployeeMapper.insertEmployee", employee);
		}

		// 직원 수정
		public void updateEmployee(Employee employee) {
			sqlSessionTemplate.update("EmployeeMapper.updateEmployee", employee);
		}

		// 직원 삭제
		public void deleteEmployee(UUID uuid) {
			sqlSessionTemplate.delete("EmployeeMapper.deleteEmployee", uuid);
		}

		// 직원 조회
		public Employee selectEmployeeByUuid(UUID uuid) {
			return sqlSessionTemplate.selectOne("EmployeeMapper.selectEmployeeByUuid", uuid);
		}
		
		// 직원 회사 조회
		public List<Employee> selectAllEmployees(){
			return sqlSessionTemplate.selectList("EmployeeMapper.selectAllEmployees");
		}
}