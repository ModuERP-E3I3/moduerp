package com.e3i3.moduerp.productionstock.service;

import java.util.List;

import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

public interface ProductionStockOutService {
	List<ProductionStockOutDTO> getAllProductionStockOuts();

	void insertProductionStockOut(ProductionStockOutDTO productionStockOutDTO);

	List<ProductionStockOutDTO> getProductionStockOutDetails(String itemCode);

	ProductionStockOutDTO getProductionStockOutDetailssSub(String pStockOutId);

	void updateProductionStockOut(ProductionStockOutDTO productionStockOutDTO);

	// itemCode에 따른 총 출고 수량 구하기
	int getTotalStockOutByItemCode(String itemCode);

	void deleteProductionStockOut(String pStockOutId);

	ProductionStockOutDTO getMostRecentStockOutDetails(String itemCode);
}
