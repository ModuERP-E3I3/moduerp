package com.e3i3.moduerp.account.service;

import java.util.List;

import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    void accountCreate(AccountDTO accountDto);
    List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<AccountDTO> getAccountsByBizNumber(String bizNumber);
    List<Employee> getEmpNameDepart(String bizNumber);
    
//    AccountNo
    AccountDTO getAccountListDetail(String accountNo);
    void updateAccount(AccountDTO accountDto);
    void deleteAccountByAccountNo(String accountNo);

}
