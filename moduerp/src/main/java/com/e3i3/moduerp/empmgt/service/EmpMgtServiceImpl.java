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
    public List<EmpMgtDTO> getEmployeesByFilterDate(String bizNumber, String option, String filterText, String startDate, String endDate) {
        if (option.equals("empName")) {
            return empMgtDao.getEmployeesByEmpNameDate(bizNumber, filterText, startDate, endDate);
        } else if (option.equals("departmentId")) {
            return empMgtDao.getEmployeesByDepartmentIdDate(bizNumber, filterText, startDate, endDate);
        } else if (option.equals("jobId")) {
            return empMgtDao.getEmployeesByJobIdDate(bizNumber, filterText, startDate, endDate);
        }

        return null;
    }

    @Override
    public List<EmpMgtDTO> getEmployeesByFilter(String bizNumber, String option, String filterText) {
        if (option.equals("empName")) {
            return empMgtDao.getEmployeesByEmpName(bizNumber, filterText);
        } else if (option.equals("departmentId")) {
            return empMgtDao.getEmployeesByDepartmentId(bizNumber, filterText);
        } else if (option.equals("jobId")) {
            return empMgtDao.getEmployeesByJobId(bizNumber, filterText);
        }
        return null;
    }

    @Override
    public List<EmpMgtDTO> getEmployeesByFilterOnlyDate(String bizNumber, String startDate, String endDate) {
        return empMgtDao.getEmployeesByFilterOnlyDate(bizNumber, startDate, endDate);
    }

    @Override
    public List<EmpMgtDTO> getEmployeesByFilterStartDate(String bizNumber, String startDate) {
        return empMgtDao.getEmployeesByFilterStartDate(bizNumber, startDate);
    }

    @Override
    public List<EmpMgtDTO> getEmployeesByFilterEndDate(String bizNumber, String endDate) {
        return empMgtDao.getEmployeesByFilterEndDate(bizNumber, endDate);
    }
}
