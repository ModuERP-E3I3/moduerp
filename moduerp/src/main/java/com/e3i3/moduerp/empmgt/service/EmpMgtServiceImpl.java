package com.e3i3.moduerp.empmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.empmgt.model.dao.EmpMgtDAO;
import com.e3i3.moduerp.empmgt.model.dto.EmpMgtDTO;

@Service
public class EmpMgtServiceImpl implements EmpMgtService {

    @Autowired
    private EmpMgtDAO empMgtDao;

    @Override
    public List<EmpMgtDTO> getAllEmployees() {
        return empMgtDao.getAllEmployees();
    }

    @Override
    public void createEmployee(EmpMgtDTO empMgtDTO) {
        empMgtDao.createEmployee(empMgtDTO);
    }

    @Override
    public List<String> getEmpNamesByBizNumber(String bizNumber) {
        return empMgtDao.getEmpNamesByBizNumber(bizNumber);
    }

    @Override
    public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
        return empMgtDao.getDepartmentIdsByBizNumber(bizNumber);
    }

    @Override
    public List<EmpMgtDTO> getEmployeesByBizNumber(String bizNumber) {
        return empMgtDao.getEmployeesByBizNumber(bizNumber);
    }

    @Override
    public List<Employee> getEmpNameDepart(String bizNumber) {
        return empMgtDao.getEmpNameDepart(bizNumber);
    }

    @Override
    public EmpMgtDTO getEmployeeDetail(String empNo) {
        return empMgtDao.selectEmployeeByEmpNo(empNo);
    }

    @Override
    public void updateEmployee(EmpMgtDTO empMgtDTO) {
        empMgtDao.updateEmployee(empMgtDTO);
    }

    @Override
    public void deleteEmployeeByEmpNo(String empNo) {
        empMgtDao.deleteEmployeeByEmpNo(empNo);
    }

    // 필터 관련 메서드
    @Override
    public List<EmpMgtDTO> getEmployeesByFilter(String bizNumber, String option, String filterText) {
        if (option.equals("empName")) {
            return empMgtDao.getEmployeesByEmpName(bizNumber, filterText);
        } else if (option.equals("departmentId")) {
            return getEmployeesByDepartmentId(bizNumber, filterText);
        } else if (option.equals("jobId")) {
            return getEmployeesByJobId(bizNumber, filterText);
        }
        return null;
    }

    // 부서 ID로 직원 목록 가져오기
    @Override
    public List<EmpMgtDTO> getEmployeesByDepartmentId(String bizNumber, String departmentId) {
        return empMgtDao.getEmployeesByDepartmentId(bizNumber, departmentId);
    }

    // 직급 ID로 직원 목록 가져오기
    @Override
    public List<EmpMgtDTO> getEmployeesByJobId(String bizNumber, String jobId) {
        return empMgtDao.getEmployeesByJobId(bizNumber, jobId);
    }
}
