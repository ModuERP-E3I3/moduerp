package com.e3i3.moduerp.empmgt.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.empmgt.model.dto.EmpMgtDTO;

@Repository
public class EmpMgtDAOImpl implements EmpMgtDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "EmpMgtMapper";

    @Override
    public List<EmpMgtDTO> getAllEmployees() {
        return sqlSession.selectList(namespace + ".getAllEmployees");
    }

    @Override
    public void createEmployee(EmpMgtDTO empMgtDTO) {
        sqlSession.insert(namespace + ".createEmployee", empMgtDTO);
    }

    @Override
    public List<String> getEmpNamesByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getEmpNamesByBizNumber", bizNumber);
    }

    @Override
    public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getDepartmentIdsByBizNumber", bizNumber);
    }

    @Override
    public List<EmpMgtDTO> getEmployeesByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getEmployeesByBizNumber", bizNumber);
    }

    @Override
    public List<Employee> getEmpNameDepart(String bizNumber) {
        return sqlSession.selectList(namespace + ".getEmpNameDepart", bizNumber);
    }

    @Override
    public EmpMgtDTO selectEmployeeByUUID(String uuid) {
        return sqlSession.selectOne(namespace + ".selectEmployeeByUUID", uuid);
    }

    @Override
    public void updateEmployee(EmpMgtDTO empMgtDTO) {
        sqlSession.update(namespace + ".updateEmployee", empMgtDTO);
    }

    @Override
    public void deleteEmployeeByUUID(String uuid) {
        sqlSession.delete(namespace + ".deleteEmployeeByUUID", uuid);
    }

    // -----------------------------------------------
    // 직원 필터링 관련 메서드

    @Override
    public List<EmpMgtDTO> getEmployeesByEmpName(String bizNumber, String filterText) {
        return sqlSession.selectList(namespace + ".selectEmployeesByEmpName",
            Map.of("bizNumber", bizNumber, "filterText", filterText));
    }

    @Override
    public List<EmpMgtDTO> getEmployeesByDepartmentName(String bizNumber, String filterText) {
        return sqlSession.selectList(namespace + ".selectEmployeesByDepartmentName",
            Map.of("bizNumber", bizNumber, "filterText", filterText));
    }

    @Override
    public List<EmpMgtDTO> getEmployeesByJobId(String bizNumber, String filterText) {
        return sqlSession.selectList(namespace + ".selectEmployeesByJobId",
            Map.of("bizNumber", bizNumber, "filterText", filterText));
    }
}
