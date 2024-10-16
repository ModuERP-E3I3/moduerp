package com.e3i3.moduerp.financialclosing.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.bankaccountmanagement.model.dto.Bankmg;
import com.e3i3.moduerp.financialclosing.model.dto.FinClose;

@Repository("finCloseDao")
public class FinCloseDao {
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
    public List<FinClose> getAllFinCloses() {
		return sqlSessionTemplate.selectList("finCloseMapper.getAllFinCloses");
	}
    public FinClose getFinCloseById(String FinCloseId) {
		return sqlSessionTemplate.selectOne("finCloseMapper.getFinCloseById", FinCloseId);
	}
    public int insertFinClose(FinClose FinClose) {
		return sqlSessionTemplate.insert("finCloseMapper.insertFinClose", FinClose);
	}
    public int updateFinClose(FinClose FinClose) {
		return sqlSessionTemplate.update("finCloseMapper.updateFinClose", FinClose);
	}
    public int deleteFinClose(String FinCloseId) {
		return sqlSessionTemplate.delete("finCloseMapper.deleteFinClose", FinCloseId);
	}
    
	public List<Bankmg> getBankmgListByBizNumber(String bizNumber) {
		return sqlSessionTemplate.selectList("finCloseMapper.getBankmgListByBizNumber", bizNumber);
	}
   
}
