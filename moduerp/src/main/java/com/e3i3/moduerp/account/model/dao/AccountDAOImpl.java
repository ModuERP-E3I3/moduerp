package com.e3i3.moduerp.account.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.account.model.dto.AccountDTO;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "AccountMapper";

    @Override
    public List<AccountDTO> selectAllAccounts() {
        return sqlSession.selectList(namespace + ".selectAllAccounts");
    }
}
