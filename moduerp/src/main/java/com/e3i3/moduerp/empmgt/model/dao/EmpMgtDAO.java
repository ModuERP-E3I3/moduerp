package com.e3i3.moduerp.empmgt.model.dao;

import java.util.List;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.empmgt.model.dto.EmpMgtDTO;

public interface EmpMgtDAO {

    // 모든 직원 가져오기
    List<EmpMgtDTO> getAllEmployees();

    // 직원 등록
    void createEmployee(EmpMgtDTO empMgtDTO);

    // 사업자 번호에 따른 직원 이름 가져오기
    List<String> getEmpNamesByBizNumber(String bizNumber);

    // 사업자 번호에 따른 부서 코드 가져오기
    List<String> getDepartmentIdsByBizNumber(String bizNumber);

    // 사업자 번호에 따른 직원 목록 가져오기
    List<EmpMgtDTO> getEmployeesByBizNumber(String bizNumber);

    // 부서 및 직급 정보를 함께 가져오기
    List<Employee> getEmpNameDepart(String bizNumber);

    // 사번으로 직원 상세 정보 가져오기
    EmpMgtDTO selectEmployeeByEmpNo(String empNo);

    // 직원 정보 수정
    void updateEmployee(EmpMgtDTO empMgtDTO);

    // 사번으로 직원 삭제
    void deleteEmployeeByEmpNo(String empNo);

    // -----------------------------------------------
    // 직원 필터링
    List<EmpMgtDTO> getEmployeesByEmpName(String bizNumber, String filterText);

    List<EmpMgtDTO> getEmployeesByDepartmentId(String bizNumber, String filterText);

    List<EmpMgtDTO> getEmployeesByJobId(String bizNumber, String filterText);


}
