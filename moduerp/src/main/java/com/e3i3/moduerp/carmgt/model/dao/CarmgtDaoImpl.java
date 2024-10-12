package com.e3i3.moduerp.carmgt.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Repository
public class CarmgtDaoImpl implements CarmgtDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "CarmgtMapper";
	
	@Override
	public List<CarmgtDto> getAllCarmgt(String bizNumber){
		return sqlSession.selectList(namespace +  ".getAllCarmgt", bizNumber);
	}
	
	@Override
	public void insertCarmgt(CarmgtDto carmgtDto) {
		sqlSession.insert(namespace + ".insertCarmgt", carmgtDto);
	}
	
	public CarmgtDto getCarmgtId(String carmgtId) {
		return sqlSession.selectOne(namespace + ".getCarmgtId", carmgtId);
	}

	/*
	 * @Override public void deleteCarmgt(String carId) {
	 * sqlSession.delete(namespace + ".deleteCarmgt", carId);
	 * 
	 * }
	 */
	
	@Override
    public List<String> getEmpNamesByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getEmpNamesByBizNumber", bizNumber);
    }

    @Override
    public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getDepartmentIdsByBizNumber", bizNumber);
    }

    @Override
    public List<CarmgtDto> getCarsByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getCarsByBizNumber", bizNumber);
    }

	@Override
	public List<Employee> getEmpNameDepart(String bizNumber) {
		return sqlSession.selectList(namespace + ".getEmpNameDepart" , bizNumber);
	}

	@Override
	public CarmgtDto selectpaymentHistoryCode(String paymentHistoryCode) {
		return sqlSession.selectOne(namespace + ".selectpaymentHistoryCode", paymentHistoryCode);
	}

	@Override
	public void updateCarmgt(CarmgtDto carmgtDto) {
		sqlSession.update(namespace + ".updateCarmgt", carmgtDto);	
	}

	@Override
	public void deleteCarmgt(String paymentHistoryCode) {
		sqlSession.delete(namespace + ".deleteCarmgt", paymentHistoryCode);
	}

    
}
