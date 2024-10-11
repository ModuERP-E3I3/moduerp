package com.e3i3.moduerp.account.service;

import java.util.List;

import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    void insertAccount(AccountDTO accountDto);
    List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<AccountDTO> getAccountsByBizNumber(String bizNumber);
    List<Employee> getEmpNameDepart(String bizNumber);
    AccountDTO getAccountListDetail(String businessNumber);
    void updateAccount(AccountDTO accountDto);
    void deleteAccountByBusinessNumber(String businessNumber);

}
