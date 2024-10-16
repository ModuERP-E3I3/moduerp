package com.e3i3.moduerp.productionstock.model.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

@Repository
public class ProductionStockOutDAOImpl implements ProductionStockOutDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "ProductionStockOutMapper";

	@Override
	public List<ProductionStockOutDTO> selectAllProductionStockOuts() {
		return sqlSession.selectList(namespace + ".selectAllProductionStockOuts");
	}

	@Override
	public void insertProductionStockOut(ProductionStockOutDTO productionStockOutDTO) {
		sqlSession.insert(namespace + ".insertProductionStockOut", productionStockOutDTO);
	}

	@Override
	public List<ProductionStockOutDTO> selectProductionStockOutByItemCode(String itemCode) {
		return sqlSession.selectList(namespace + ".selectProductionStockOutByItemCode", itemCode);
	}

	public ProductionStockOutDTO SelectProductionStockOutByPStockId(String pStockOutId) {
		return sqlSession.selectOne(namespace + ".selectProductionStockOutByPStockId", pStockOutId);
	}

	@Override
	public void updateProductionStockOut(ProductionStockOutDTO productionStockOutDTO) {
		// MyBatis 매퍼 호출하여 출고 정보 업데이트
		sqlSession.update(namespace + ".updateProductionStockOut", productionStockOutDTO);
	}

	@Override
	public int selectTotalStockOutByItemCode(String itemCode) {
	    Integer totalStockOut = sqlSession.selectOne(namespace + ".getTotalStockOutByItemCode", itemCode);
	    return totalStockOut != null ? totalStockOut : 0;  // null인 경우 0을 반환
	}


	@Override
	public void deleteProductionStockOut(String pStockOutId) {
		sqlSession.delete(namespace + ".deleteProductionStockOut", pStockOutId);
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
		Map<String, Object> params = new HashMap<>();
		params.put("itemCode", itemCode);
		params.put("pStockOutPlace", data.get("P_STOCK_OUT_PLACE"));
		params.put("pStockOutPrice", data.get("P_STOCK_OUT_PRICE"));

		sqlSession.update(namespace + ".updateOutPlaceAndPriceInItem", params);
	}

	@Override
	public ProductionStockOutDTO selectMostRecentStockOutDetails(String itemCode) {
		return sqlSession.selectOne(namespace + ".selectMostRecentStockOutDetails", itemCode);
	}
}
