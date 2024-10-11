package com.e3i3.moduerp.account.model.dao;

import java.util.List;

import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface AccountDAO {
    List<AccountDTO> getAllAccounts();
    void insertAccount(AccountDTO accountDto);

    List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<AccountDTO> getAccountsByBizNumber(String bizNumber);
    
    List<Employee> getEmpNameDepart(String bizNumber);
    AccountDTO selectAccountByBusinessNumber(String businessNumber);
    void updateAccount(AccountDTO accountDto);
    void deleteAccountByBusinessNumber(String businessNumber);
}
