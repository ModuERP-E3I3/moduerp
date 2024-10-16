package com.e3i3.moduerp.carmgt.model.dao;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<CarmgtDto> getCarByCarModelDate(String bizNumber, String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByCarModelDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarmgtDto> getCarByCarNumDate(String bizNumber, String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByCarNumDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarmgtDto> getCarByEmpNameDate(String bizNumber, String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByEmpNameDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarmgtDto> getCarByDepartmentIdDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByDepartmentIdDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarmgtDto> getCarByCarModel(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByCarModel",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarmgtDto> getCarByCarNum(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByCarNum",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarmgtDto> getCarByEmpName(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByEmpName",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarmgtDto> getCarByDepartmentId(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByDepartmentId",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarmgtDto> getCarByFilterOnlyDate(String bizNumber, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByFilterOnlyDate",
				Map.of("bizNumber", bizNumber, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarmgtDto> getCarByFilterStartDate(String bizNumber, String startDate) {
		return sqlSession.selectList(namespace + ".selectCarByFilterStartDate",
				Map.of("bizNumber", bizNumber, "startDate", startDate));
	}

	@Override
	public List<CarmgtDto> getCarByFilterEndDate(String bizNumber, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByFilterEndDate",
				Map.of("bizNumber", bizNumber, "endDate", endDate));
	}

    
}
