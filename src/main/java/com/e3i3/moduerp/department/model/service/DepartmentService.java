package com.e3i3.moduerp.department.model.service;

import java.util.List;
import com.e3i3.moduerp.department.model.dto.Department;

public interface DepartmentService {
    // 부서 등록
    void insertDepartment(Department department);

    // 특정 사업자 번호에 대한 모든 부서 조회
    List<Department> selectDepartmentsByBizNumber(String bizNumber);
    
    // 특정 부서 삭제
    int deleteDepartmentById(String departmentId);

    // 모든 부서 조회
    List<Department> selectAllDepartments();

    // 부서 정보 업데이트
    int updateDepartment(Department department);
}