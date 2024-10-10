package com.e3i3.moduerp.carmgt.model.service;

import java.util.List;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;

public interface CarmgtService {
	List<CarmgtDto> getAllCarmgt();
	void insertCarmgt(CarmgtDto carmgtDto);
	List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<CarmgtDto> getCarsByBizNumber(String bizNumber);
	
	/* void deleteCarmgt(String carId); */
}
