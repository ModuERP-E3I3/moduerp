package com.e3i3.moduerp.bankaccountmanagement.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.bankaccountmanagement.model.dao.BankmgDao;
import com.e3i3.moduerp.bankaccountmanagement.model.dto.Bankmg;

@Service("bankmgService")
public class BankmgServiceImpl implements BankmgService {

	@Autowired
	private BankmgDao bankmgDAO;

	@Override
	public List<Bankmg> getAllmgs() {
		return bankmgDAO.getAllmgs();
	}

	@Override
	public void insertmg(Bankmg mg) {
		bankmgDAO.insertmg(mg);
	}

	@Override
	public void updatemg(Bankmg mg) {
		bankmgDAO.updatemg(mg);
	}

	@Override
	public void deletemg(Bankmg mg) {
		bankmgDAO.deletemg(mg);
	}

	@Override
	public List<Bankmg> getmgById(String bankNumber, String bizNumber) {
		return bankmgDAO.getmgById(bankNumber, bizNumber);
	}

}
