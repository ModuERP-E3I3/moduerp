package com.e3i3.moduerp.empmgt.service;

import java.util.List;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.empmgt.model.dto.EmpMgtDTO;
import com.e3i3.moduerp.department.model.dto.Department; // 부서 DTO 임포트

public interface EmpMgtService {

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

    // 부서와 직급 정보를 함께 가져오기
    List<Employee> getEmpNameDepart(String bizNumber);

    // UUID로 직원 상세 정보 가져오기
    EmpMgtDTO getEmployeeDetailByUUID(String uuid);

    // 직원 정보 수정
    void updateEmployee(EmpMgtDTO empMgtDTO);

    // UUID로 직원 삭제
    void deleteEmployeeByUUID(String uuid);

    // 필터: 부서명으로 직원 목록 가져오기
    List<EmpMgtDTO> getEmployeesByDepartmentName(String bizNumber, String departmentName);

    // 필터로 직원 목록 가져오기
    List<EmpMgtDTO> getEmployeesByFilter(String bizNumber, String option, String filterText);

    // 직급 ID로 직원 목록 가져오기
    List<EmpMgtDTO> getEmployeesByJobId(String bizNumber, String jobId);


    // 모든 부서 목록 가져오기
    List<Department> getAllDepartments();  // 부서 목록 가져오는 메서드 추가

    
    
	String getApprovalCodeByBizNumber(String bizNumber);
}





