package com.e3i3.moduerp.buystock.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.buystock.model.dto.BuyStockOutDTO;



@Repository
public class BuyStockOutDaoImpl implements BuyStockOutDao{

	 @Autowired
	    private SqlSession sqlSession;

	    private static final String namespace = "BuyStockOutMapper";

	    @Override
	    public List<BuyStockOutDTO> selectAllBuyStockOuts() {
	        return sqlSession.selectList(namespace + ".selectAllBuyStockOuts");
	    }
	
}
