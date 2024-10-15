package com.e3i3.moduerp.bankaccountmanagement.model.service;

import java.util.List;

import com.e3i3.moduerp.bankaccountmanagement.model.dto.Bankmg;

public interface BankmgService {
    List<Bankmg> getAllmgs();
    void insertmg(Bankmg mg);
    void updatemg(Bankmg mg);
    void deletemg(Bankmg mg);
	List<Bankmg> getmgById(String bankNumber, String bizNumber);
}
