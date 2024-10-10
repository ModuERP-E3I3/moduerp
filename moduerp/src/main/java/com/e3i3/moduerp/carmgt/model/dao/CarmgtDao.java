package com.e3i3.moduerp.carmgt.model.dao;

import java.util.List;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;

public interface CarmgtDao {
	List<CarmgtDto> getAllCarmgt();
	void insertCarmgt(CarmgtDto carmgtDto);
	/* void deleteCarmgt(String carId); */
	
	List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<CarmgtDto> getCarsByBizNumber(String bizNumber);
}
