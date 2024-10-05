package com.e3i3.moduerp.account.model.dao;

import java.util.List;
import com.e3i3.moduerp.account.model.dto.AccountDTO;

public interface AccountDAO {
    List<AccountDTO> selectAllAccounts();
}
