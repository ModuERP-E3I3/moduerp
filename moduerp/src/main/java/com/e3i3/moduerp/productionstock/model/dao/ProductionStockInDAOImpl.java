package com.e3i3.moduerp.productionstock.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;

@Repository
public class ProductionStockInDAOImpl implements ProductionStockInDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "ProductionStockMapper";

	@Override
	public List<ProductionStockInDTO> getAllProductionStockIn() {
		return sqlSession.selectList(namespace + ".getAllProductionStockIn");
	}

	@Override
	public void insertProductionStockIn(ProductionStockInDTO productionStockInDTO) {
		sqlSession.insert(namespace + ".insertProductionStockIn", productionStockInDTO);
	}

	@Override
	public ProductionStockInDTO selectProductionStockInByItemCode(String itemCode) {
		return sqlSession.selectOne(namespace + ".selectProductionStockInByItemCode", itemCode);
	}

	@Override
	public void updateProductionStockIn(ProductionStockInDTO productionStockInDTO) {
		sqlSession.update(namespace + ".updateProductionStockIn", productionStockInDTO);
	}
}
