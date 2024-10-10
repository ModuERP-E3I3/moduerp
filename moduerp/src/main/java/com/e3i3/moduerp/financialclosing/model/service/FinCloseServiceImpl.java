package com.e3i3.moduerp.financialclosing.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.financialclosing.model.dao.FinCloseDao;
import com.e3i3.moduerp.financialclosing.model.dto.FinClose;

@Service("finCloseService")
public class FinCloseServiceImpl implements FinCloseService {
	@Autowired
	private FinCloseDao FinCloseDAO;

	@Override
	public List<FinClose> getAllFinCloses() {
		return FinCloseDAO.getAllFinCloses();
	}

	@Override
	public FinClose getFinCloseById(String FinCloseId) {
		return FinCloseDAO.getFinCloseById(FinCloseId);
	}

	@Override
	public void insertFinClose(FinClose FinClose) {
		FinCloseDAO.insertFinClose(FinClose);
	}

	@Override
	public void updateFinClose(FinClose FinClose) {
		FinCloseDAO.updateFinClose(FinClose);
	}

	@Override
	public void deleteFinClose(String FinCloseId) {
		FinCloseDAO.deleteFinClose(FinCloseId);
	}
}
