package com.e3i3.moduerp.bankaccountmanagement.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.bankaccountmanagement.model.dto.Bankmg;

@Repository("bankmgDao")
public class BankmgDao {
	
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
    public List<Bankmg> getAllmgs() {
		return sqlSessionTemplate.selectList("bankmgMapper.getAllmgs");
	}
    public Bankmg getmgById(String bankId) {
		return sqlSessionTemplate.selectOne("bankmgMapper.getmgById", bankId);
	}
    public int insertmg(Bankmg mg) {
		return sqlSessionTemplate.insert("bankmgMapper.insertmg", mg);
	}
    public int updatemg(Bankmg mg) {
		return sqlSessionTemplate.update("bankmgMapper.updatemg", mg);
	}
    public int deletemg(String bankId) {
		return sqlSessionTemplate.delete("bankmgMapper.deletemg", bankId);
	}
   
}
