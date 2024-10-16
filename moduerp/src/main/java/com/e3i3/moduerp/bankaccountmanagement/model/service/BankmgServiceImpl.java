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
	public List<Bankmg> selectAllmgs() {
		return bankmgDAO.selectAllmgs();
	}
	
	@Override
	public void insertmg(Bankmg mg) {
		bankmgDAO.insertmg(mg);
	}

	@Override
	public void updatemg(String bankId) {
		bankmgDAO.updatemg(bankId);
	}

	@Override
	public void deletemg(String bankId) {
		bankmgDAO.deletemg(bankId);
	}

	@Override
	public Bankmg selectmgById(String bankId) {
		return bankmgDAO.getmgById(bankId);
	}

}
