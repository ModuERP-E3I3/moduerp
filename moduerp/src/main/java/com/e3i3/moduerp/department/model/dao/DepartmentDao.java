package com.e3i3.moduerp.department.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.department.model.dto.Department;

@Repository
public class DepartmentDao {
	// root-context.xml에 설정된 마이바티스 객체를 연결 사용함
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 부서 등록
	public void insertDepartment(Department department) {
		sqlSessionTemplate.insert("DepartmentMapper.insertDepartment", department);
	}

	// 사업자번호로 회사의 전체 부서 조회
	public List<Department> selectDepartmentsByBizNumber(String bizNumber) {
		return sqlSessionTemplate.selectList("DepartmentMapper.selectDepartmentsByBizNumber", bizNumber);
	}

	// 모든 부서 조회
	public List<Department> selectAllDepartments() {
		return sqlSessionTemplate.selectList("DepartmentMapper.selectAllDepartments");
	}

	// 특정 부서 삭제
	public int deleteDepartmentById(String departmentId) {
		return sqlSessionTemplate.delete("DepartmentMapper.deleteDepartmentById", departmentId);
	}

	// 특정 부서 정보 업데이트
	public int updateDepartment(Department department) {
		return sqlSessionTemplate.update("DepartmentMapper.updateDepartment", department);
	}
}