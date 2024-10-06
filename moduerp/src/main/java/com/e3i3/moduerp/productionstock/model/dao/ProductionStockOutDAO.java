package com.e3i3.moduerp.productionstock.model.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

public interface ProductionStockOutDAO {
	List<ProductionStockOutDTO> selectAllProductionStockOuts();

	void insertProductionStockOut(ProductionStockOutDTO productionStockOutDTO);

	List<ProductionStockOutDTO> selectProductionStockOutByItemCode(String itemCode);

	ProductionStockOutDTO SelectProductionStockOutByPStockId(String pStockOutId);

	void updateProductionStockOut(ProductionStockOutDTO productionStockOutDTO);

	// itemCode에 따른 총 출고 수량 구하기
	int selectTotalStockOutByItemCode(String itemCode);

	void deleteProductionStockOut(String pStockOutId);

	public Timestamp getLatestStockOutDateByItemCode(String itemCode);

	public double getLatestOutPriceByItemCode(String itemCode);

	Map<String, Object> getLatestOutPlaceAndPriceByItemCode(String itemCode);

	void updateOutPlaceAndPriceInItem(String itemCode, Map<String, Object> data);

	ProductionStockOutDTO selectMostRecentStockOutDetails(String itemCode);

}
