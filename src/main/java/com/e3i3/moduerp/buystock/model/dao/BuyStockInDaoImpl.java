package com.e3i3.moduerp.buystock.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.buystock.model.dto.BuyStockInDTO;

@Repository
public class BuyStockInDaoImpl implements BuyStockInDao{

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "BuyStockMapper";
	
	@Override
	public List<BuyStockInDTO> getAllBuyStockIn() {
		return sqlSession.selectList(namespace + ".getAllBuyStockIn");
	}
	
	public BuyStockInDTO getBuyStockInById(String bStockInId) {
        return sqlSession.selectOne(namespace + ".getBuyStockInById", bStockInId);
    }

}
