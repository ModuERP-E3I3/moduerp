package com.e3i3.moduerp.empmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.empmgt.model.dao.EmpMgtDAO;
import com.e3i3.moduerp.department.model.dto.Department; // 부서 DTO 임포트

@Service
public class EmpMgtServiceImpl implements EmpMgtService {

    @Autowired
    private EmpMgtDAO empMgtDao;
    @Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;


    @Override
    public List<Employee> getAllEmployees() {
        return empMgtDao.getAllEmployees();
    }

    @Override
    public void createEmployee(Employee empMgtDTO) {
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
    public List<Employee> getEmployeesByBizNumber(String bizNumber) {
        return empMgtDao.getEmployeesByBizNumber(bizNumber);
    }

    @Override
    public List<Employee> getEmpNameDepart(String bizNumber) {
        return empMgtDao.getEmpNameDepart(bizNumber);
    }

    @Override
    public void updateEmployee(Employee empMgtDTO) {
        empMgtDao.updateEmployee(empMgtDTO);
    }

    @Override
    public void deleteEmployeeByUUID(String uuid) {
        empMgtDao.deleteEmployeeByUUID(uuid);
    }

    @Override
    public Employee getEmployeeDetailByUUID(String uuid) {
        return empMgtDao.selectEmployeeByUUID(uuid);
    }

    // 필터 관련 메서드
    @Override
    public List<Employee> getEmployeesByFilter(String bizNumber, String option, String filterText) {
        if (option.equals("empName")) {
            return empMgtDao.getEmployeesByEmpName(bizNumber, filterText);
        } else if (option.equals("departmentName")) { // 부서명 필터 추가
            return empMgtDao.getEmployeesByDepartmentName(bizNumber, filterText); // 부서명으로 필터링
        } else if (option.equals("jobId")) {
            return getEmployeesByJobId(bizNumber, filterText);
        }
        return null;
    }

    // 직급 ID로 직원 목록 가져오기
    @Override
    public List<Employee> getEmployeesByJobId(String bizNumber, String jobId) {
        return empMgtDao.getEmployeesByJobId(bizNumber, jobId);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentName(String bizNumber, String departmentName) {
        return empMgtDao.getEmployeesByDepartmentName(bizNumber, departmentName);
    }

    
    // 모든 부서 목록 가져오기
    @Override
    public List<Department> getAllDepartments() {
        return empMgtDao.getAllDepartments();  // 부서 목록을 가져오는 DAO 호출
    }

    // **bizNumber로 approvalCode 가져오기**
    @Override
    public String getApprovalCodeByBizNumber(String bizNumber) {
        return empMgtDao.getApprovalCodeByBizNumber(bizNumber);  // approvalCode 가져오는 DAO 호출
    }
}
