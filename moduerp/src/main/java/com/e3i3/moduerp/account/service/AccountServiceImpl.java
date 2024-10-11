package com.e3i3.moduerp.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.account.model.dao.AccountDAO;
import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDao;
   
    
    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @Override
    public void insertAccount(AccountDTO accountDto) {
        accountDao.insertAccount(accountDto);
    }

    @Override
    public List<String> getEmpNamesByBizNumber(String bizNumber) {
        return accountDao.getEmpNamesByBizNumber(bizNumber);
    }

    @Override
    public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
        return accountDao.getDepartmentIdsByBizNumber(bizNumber);
    }

    @Override
    public List<AccountDTO> getAccountsByBizNumber(String bizNumber) {
        return accountDao.getAccountsByBizNumber(bizNumber);
    }

    @Override
    public List<Employee> getEmpNameDepart(String bizNumber) {
        return accountDao.getEmpNameDepart(bizNumber);
    }

    @Override
    public AccountDTO getAccountListDetail(String businessNumber) {
        return accountDao.selectAccountByBusinessNumber(businessNumber);
    }

    @Override
    public void updateAccount(AccountDTO accountDto) {
        accountDao.updateAccount(accountDto);
    }

    @Override
    public void deleteAccountByBusinessNumber(String businessNumber) {
        accountDao.deleteAccountByBusinessNumber(businessNumber);
    }

}
