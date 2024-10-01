package com.e3i3.moduerp.employee.model.dao;

import java.util.List;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.employee.model.dto.Employee;

@Repository
public class EmployeeDao {
	// root-context.xml�� ������ ���̹�Ƽ�� ��ü�� ���� �����
		@Autowired
		private SqlSessionTemplate sqlSessionTemplate;

		// ���� ���
		public void insertEmployee(Employee employee) {
			sqlSessionTemplate.insert("EmployeeMapper.insertEmployee", employee);
		}

		// ���� ����
		public void updateEmployee(Employee employee) {
			sqlSessionTemplate.update("EmployeeMapper.updateEmployee", employee);
		}

		// ���� ����
		public void deleteEmployee(UUID uuid) {
			sqlSessionTemplate.delete("EmployeeMapper.deleteEmployee", uuid);
		}

		// ���� ��ȸ
		public Employee selectEmployeeByUuid(UUID uuid) {
			return sqlSessionTemplate.selectOne("EmployeeMapper.selectEmployeeByUuid", uuid);
		}
		
		// ���� ȸ�� ��ȸ
		public List<Employee> selectAllEmployees(){
			return sqlSessionTemplate.selectList("EmployeeMapper.selectAllEmployees");
		}
}