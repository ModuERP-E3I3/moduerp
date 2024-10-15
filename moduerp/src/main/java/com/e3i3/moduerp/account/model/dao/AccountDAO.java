package com.e3i3.moduerp.account.model.dao;

import java.util.List;

import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface AccountDAO {
	List<AccountDTO> getAllAccounts();

	void accountCreate(AccountDTO accountDto);

	List<String> getEmpNamesByBizNumber(String bizNumber);

	List<String> getDepartmentIdsByBizNumber(String bizNumber);

	List<AccountDTO> getAccountsByBizNumber(String bizNumber);

	List<Employee> getEmpNameDepart(String bizNumber);

//    AccountNo
	AccountDTO selectAccountByAccountNo(String accountNo);

	void updateAccount(AccountDTO accountDto);

	void deleteAccountByAccountNo(String accountNo);

	
	// filter !!!!!!!!!!!

	// 텍스트 필터만 있는 경우
	List<AccountDTO> getAccountsByAccountName(String bizNumber, String filterText);

	List<AccountDTO> getAccountsByBusinessNumber(String bizNumber, String filterText);

	List<AccountDTO> getAccountsByBossName(String bizNumber, String filterText);

}




