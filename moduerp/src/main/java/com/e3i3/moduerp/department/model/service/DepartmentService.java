package com.e3i3.moduerp.department.model.service;

import java.util.List;
import com.e3i3.moduerp.department.model.dto.Department;

public interface DepartmentService {
    // �μ� ���
    void insertDepartment(Department department);

    // Ư�� ����� ��ȣ�� ���� ��� �μ� ��ȸ
    List<Department> selectDepartmentsByBizNumber(String bizNumber);
    
    // Ư�� �μ� ����
    int deleteDepartmentById(String departmentId);

    // ��� �μ� ��ȸ
    List<Department> selectAllDepartments();

    // �μ� ���� ������Ʈ
    int updateDepartment(Department department);
}