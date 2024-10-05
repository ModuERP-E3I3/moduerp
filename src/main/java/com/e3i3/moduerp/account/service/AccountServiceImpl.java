package com.e3i3.moduerp.account.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.account.model.dao.AccountDAO;
import com.e3i3.moduerp.account.model.dto.AccountDTO;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountDAO.selectAllAccounts();
    }
}
