package com.e3i3.moduerp.bankaccountmanagement.model.service;

import java.util.List;

import com.e3i3.moduerp.bankaccountmanagement.model.dto.Bankmg;

public interface BankmgService {
    List<Bankmg> getAllmgs();
    Bankmg getmgById(String bankId);
    void insertmg(Bankmg mg);
    void updatemg(Bankmg mg);
    void deletemg(String bankId);
}
