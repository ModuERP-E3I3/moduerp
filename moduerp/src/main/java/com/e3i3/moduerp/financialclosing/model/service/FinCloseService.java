package com.e3i3.moduerp.financialclosing.model.service;

import java.util.List;

import com.e3i3.moduerp.financialclosing.model.dto.FinClose;


public interface FinCloseService {
	List<FinClose> getAllFinCloses();
	FinClose getFinCloseById(String FinCloseId);
    void insertFinClose(FinClose FinClose);
    void updateFinClose(FinClose FinClose);
    void deleteFinClose(String FinCloseId);
}
