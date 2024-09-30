package com.e3i3.moduerp.department.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.department.model.dto.Department;

@Repository
public class DepartmentDao {
	// root-context.xml�� ������ ���̹�Ƽ�� ��ü�� ���� �����
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// �μ� ���
	public void insertDepartment(Department department) {
		sqlSessionTemplate.insert("DepartmentMapper.insertDepartment", department);
	}

	// ����ڹ�ȣ�� ȸ���� ��ü �μ� ��ȸ
	public List<Department> selectDepartmentsByBizNumber(String bizNumber) {
		return sqlSessionTemplate.selectList("DepartmentMapper.selectDepartmentsByBizNumber", bizNumber);
	}

	// ��� �μ� ��ȸ
	public List<Department> selectAllDepartments() {
		return sqlSessionTemplate.selectList("DepartmentMapper.selectAllDepartments");
	}

	// Ư�� �μ� ����
	public int deleteDepartmentById(String departmentId) {
		return sqlSessionTemplate.delete("DepartmentMapper.deleteDepartmentById", departmentId);
	}

	// Ư�� �μ� ���� ������Ʈ
	public int updateDepartment(Department department) {
		return sqlSessionTemplate.update("DepartmentMapper.updateDepartment", department);
	}
}