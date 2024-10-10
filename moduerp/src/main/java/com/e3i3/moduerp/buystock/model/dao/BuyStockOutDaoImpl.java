package com.e3i3.moduerp.buystock.model.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		@Override
		public void insertBuyStockOut(BuyStockOutDTO buyStockOutDTO) {
			sqlSession.insert(namespace + ".insertBuyStockOut", buyStockOutDTO);
			
		}

		@Override
		public List<BuyStockOutDTO> selectBuyStockOutByItemCode(String itemCode) {
			return sqlSession.selectList(namespace + ".selectAllBuyStockOuts");
		}

		@Override
		public BuyStockOutDTO SelectBuyStockOutByBStockId(String bStockOutId) {
			return sqlSession.selectOne(namespace + ".selectBuyStockOutByBStockId", bStockOutId);
		}

		@Override
		public void updateBuyStockOut(BuyStockOutDTO buyStockOutDTO) {
			sqlSession.update(namespace + ".updateBuyStockOut", buyStockOutDTO);
		}

		
		@Override
		public int selectTotalStockOutByItemCode(String itemCode) {
		    Integer totalStockOut = sqlSession.selectOne(namespace + ".getTotalStockOutByItemCode", itemCode);
		    return totalStockOut != null ? totalStockOut : 0;  
		}   // null인 경우 0을 반환

		@Override
		public void deleteBuyStockOut(String bStockOutId) {
			sqlSession.delete(namespace + ".deleteBuyStockOut", bStockOutId);
			
		}

		@Override
		public Timestamp getLatestStockOutDateByItemCode(String itemCode) {
			return sqlSession.selectOne(namespace + ".selectLatestStockOutDateByItemCode", itemCode);
		}

		@Override
		public double getLatestOutPriceByItemCode(String itemCode) {
			return sqlSession.selectOne(namespace + ".selectLatestOutPriceByItemCode", itemCode);
		}

		@Override
		public Map<String, Object> getLatestOutPlaceAndPriceByItemCode(String itemCode) {
			return sqlSession.selectOne(namespace + ".selectLatestOutPlaceAndPriceByItemCode", itemCode);
		}

		@Override
		public void updateOutPlaceAndPriceInItem(String itemCode, Map<String, Object> data) {
			Map<String, Object> barams = new HashMap<>();
			barams.put("itemCode", itemCode);
			barams.put("bStockOutPlace", data.get("B_STOCK_OUT_PLACE"));
			barams.put("bStockOutPrice", data.get("B_STOCK_OUT_PRICE"));

			sqlSession.update(namespace + ".updateOutPlaceAndPriceInItem", barams);			
		}

		@Override
		public BuyStockOutDTO selectMostRecentStockOutDetails(String itemCode) {
			return sqlSession.selectOne(namespace + ".selectMostRecentStockOutDetails", itemCode);
		}
	
}
