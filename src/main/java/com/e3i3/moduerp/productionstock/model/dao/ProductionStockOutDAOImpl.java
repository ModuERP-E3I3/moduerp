package com.e3i3.moduerp.productionstock.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;
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
		return sqlSession.selectOne(namespace + ".getTotalStockOutByItemCode", itemCode);
	}
}
