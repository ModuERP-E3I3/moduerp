package com.e3i3.moduerp.employee.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.dto.EmployeeBasicInfo;

@Repository
public class EmployeeDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 직원 등록
	public void insertEmployee(Employee employee) {
		sqlSessionTemplate.insert("EmployeeMapper.insertEmployee", employee);
	}

	// 로그인 메소드
	public Employee selectEmployeeForLogin(Map<String, Object> params) {
		return sqlSessionTemplate.selectOne("EmployeeMapper.selectEmployeeForLogin", params);
	}

	// 직원 정보 업데이트
	public void updateEmployee(Employee employee) {
		sqlSessionTemplate.update("EmployeeMapper.updateEmployee", employee);
	}

	// uuid로 직원 삭제
	public void deleteEmployee(String uuid) {
		sqlSessionTemplate.delete("EmployeeMapper.deleteEmployee", uuid);
	}

	// uuid로 직원 조회
	public Employee selectEmployeeByUuid(String uuid) {
		return sqlSessionTemplate.selectOne("EmployeeMapper.selectEmployeeByUuid", uuid);
	}
	
	//사업자번호로 직원 조회
	public List<Employee> selectEmployeesByBizNum(String bizNumber) {
		return sqlSessionTemplate.selectList("EmployeeMapper.selectEmployeesByBizNum", bizNumber);
	}
	
	 /**
     * 같은 사업자 번호와 부서 ID를 가진 직원 목록 조회
     * 
     * @param bizNumber 사업자 번호
     * @param departmentId 부서 ID
     * @return List<Employee> 같은 사업자 번호와 부서 ID를 가진 직원 목록
     */
    public List<Employee> selectEmployeesByBizAndDepartment(String bizNumber, String departmentId) {
        // 파라미터를 맵 형태로 준비
        Map<String, Object> params = new HashMap<>();
        params.put("bizNumber", bizNumber);
        params.put("departmentId", departmentId);
        
        // MyBatis 쿼리 실행
        return sqlSessionTemplate.selectList("EmployeeMapper.selectEmployeesByBizAndDepartment", params);
    }
    
    /**
     * 비밀번호를 업데이트합니다.
     *
     * @param uuid     사용자의 UUID
     * @param password 암호화된 새 비밀번호
     * @return 업데이트된 행의 수
     */
    public int updatePassword(String uuid, String password) {
        // 매퍼에 전달할 파라미터를 맵으로 생성
        Map<String, Object> params = new HashMap<>();
        params.put("uuid", uuid);
        params.put("password", password);

        // 매퍼 네임스페이스와 ID를 통해 쿼리 실행
        return sqlSessionTemplate.update("EmployeeMapper.updatePassword", params);
    }
    
	// 존재하는 모든 직원 조회(필요성 검토)
	public List<Employee> selectAllEmployees() {
		return sqlSessionTemplate.selectList("EmployeeMapper.selectAllEmployees");
	}

	public List<EmployeeBasicInfo> selectEmployeesByEmailAndBizNumber(String keyword, String bizNumber) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("keyword", keyword);
	    params.put("bizNumber", bizNumber);
	    return sqlSessionTemplate.selectList("EmployeeMapper.selectEmployeesByEmailAndBizNumber", params);
	}

	
	 // 이메일과 사업자번호로 직원 정보 조회
    public Employee selectEmployeeByEmailAndBizNumber(String empEmail, String bizNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("empEmail", empEmail);
        params.put("bizNumber", bizNumber);
        return sqlSessionTemplate.selectOne("EmployeeMapper.selectEmployeeByEmailAndBizNumber", params);
    }
}