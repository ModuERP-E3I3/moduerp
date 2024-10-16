package com.e3i3.moduerp.bankaccountmanagement.model.service;

import java.util.List;

import com.e3i3.moduerp.bankaccountmanagement.model.dto.Bankmg;

public interface BankmgService {
    List<Bankmg> selectAllmgs();
    void insertmg(Bankmg mg);
    void updatemg(String bankId);
    void deletemg(String bankId);
	Bankmg selectmgById(String bankId);
	
}
