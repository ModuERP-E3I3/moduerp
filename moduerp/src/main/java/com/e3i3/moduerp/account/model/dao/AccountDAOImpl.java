package com.e3i3.moduerp.account.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Autowired
    private SqlSession sqlSession;
    
    private static final String namespace = "AccountMapper";
    
    @Override
    public List<AccountDTO> getAllAccounts() {
        return sqlSession.selectList(namespace + ".getAllAccounts");
    }
    
    @Override
    public void insertAccount(AccountDTO accountDto) {
        sqlSession.insert(namespace + ".insertAccount", accountDto);
    }
    
    public AccountDTO getAccountById(String accountId) {
        return sqlSession.selectOne(namespace + ".getAccountById", accountId);
    }

    @Override
    public List<String> getEmpNamesByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getEmpNamesByBizNumber", bizNumber);
    }

    @Override
    public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getDepartmentIdsByBizNumber", bizNumber);
    }

    @Override
    public List<AccountDTO> getAccountsByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getAccountsByBizNumber", bizNumber);
    }

    @Override
    public List<Employee> getEmpNameDepart(String bizNumber) {
        return sqlSession.selectList(namespace + ".getEmpNameDepart", bizNumber);
    }

    @Override
    public AccountDTO selectAccountByBusinessNumber(String businessNumber) {
        return sqlSession.selectOne(namespace + ".selectAccountByBusinessNumber", businessNumber);
    }

    @Override
    public void updateAccount(AccountDTO accountDto) {
        sqlSession.update(namespace + ".updateAccount", accountDto);    
    }

    @Override
    public void deleteAccountByBusinessNumber(String businessNumber) {
        sqlSession.delete(namespace + ".deleteAccountByBusinessNumber", businessNumber);
    }
}
