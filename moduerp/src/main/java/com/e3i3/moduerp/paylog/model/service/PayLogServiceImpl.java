package com.e3i3.moduerp.paylog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.paylog.model.dao.PayLogDAO;
import com.e3i3.moduerp.paylog.model.dto.PayLogDTO;

@Service
public class PayLogServiceImpl implements PayLogService {

	@Autowired
	private PayLogDAO payLogDAO;

	@Override
	public void insertPayLog(PayLogDTO payLogDTO) {
		payLogDAO.insertPayLog(payLogDTO);

	}

}
