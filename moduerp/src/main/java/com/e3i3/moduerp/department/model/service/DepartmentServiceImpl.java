package com.e3i3.moduerp.department.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.department.model.dao.DepartmentDao;
import com.e3i3.moduerp.department.model.dto.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public void insertDepartment(Department department) {
        departmentDao.insertDepartment(department);
    }

    @Override
    public List<Department> selectDepartmentsByBizNumber(String bizNumber) {
        return departmentDao.selectDepartmentsByBizNumber(bizNumber);
    }

    @Override
    public int deleteDepartmentById(String departmentId) {
        return departmentDao.deleteDepartmentById(departmentId);
    }

    @Override
    public List<Department> selectAllDepartments() {
        return departmentDao.selectAllDepartments();
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }
}
