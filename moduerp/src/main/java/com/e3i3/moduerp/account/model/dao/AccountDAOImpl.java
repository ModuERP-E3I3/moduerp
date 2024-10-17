package com.e3i3.moduerp.account.model.dao;

import java.util.List;
import java.util.Map;

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
    public List<AccountDTO> getAllAccounts(String bizNumber) {
        return sqlSession.selectList(namespace + ".getAllAccounts", bizNumber);
    }
    
    @Override
    public void accountCreate(AccountDTO accountDto) {
        sqlSession.insert(namespace + ".accountCreate", accountDto);
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
    public AccountDTO selectAccountByAccountNo(String accountNo) {
        return sqlSession.selectOne(namespace + ".selectAccountByAccountNo", accountNo);
    }

    @Override
    public void updateAccount(AccountDTO accountDto) {
        sqlSession.update(namespace + ".updateAccount", accountDto);    
    }

    @Override
    public void deleteAccountByAccountNo(String accountNo) {
        sqlSession.delete(namespace + ".deleteAccountByAccountNo", accountNo);
    }
    
    
    // filter !!!!!!!!!!
    @Override
    public List<AccountDTO> getAccountsByAccountName(String bizNumber, String filterText) {
        return sqlSession.selectList(namespace + ".selectAccountsByAccountName",
                Map.of("bizNumber", bizNumber, "filterText", filterText));
    }

    @Override
    public List<AccountDTO> getAccountsByBusinessNumber(String bizNumber, String filterText) {
        return sqlSession.selectList(namespace + ".selectAccountsByBusinessNumber",
                Map.of("bizNumber", bizNumber, "filterText", filterText));
    }

    @Override
    public List<AccountDTO> getAccountsByBossName(String bizNumber, String filterText) {
        return sqlSession.selectList(namespace + ".selectAccountsByBossName",
                Map.of("bizNumber", bizNumber, "filterText", filterText));
    }

    
    
    
    
    
    
    
}
