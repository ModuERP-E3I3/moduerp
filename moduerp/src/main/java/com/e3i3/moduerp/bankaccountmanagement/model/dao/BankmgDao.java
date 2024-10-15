package com.e3i3.moduerp.bankaccountmanagement.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Bankmg> getmgById(String bankNumber, String bizNumber) {
    	Map<String, Object> params = new HashMap<>();
        params.put("bankNumber", bankNumber);
        params.put("bizNumber", bizNumber);
		return sqlSessionTemplate.selectList("bankmgMapper.getmgById", params);
	}
    public int insertmg(Bankmg mg) {
		return sqlSessionTemplate.insert("bankmgMapper.insertmg", mg);
	}
    public int updatemg(Bankmg mg) {
		return sqlSessionTemplate.update("bankmgMapper.updatemg", mg);
	}
    public int deletemg(Bankmg mg) {
        return sqlSessionTemplate.delete("bankmgMapper.deletemg", mg);
	}
   
}
