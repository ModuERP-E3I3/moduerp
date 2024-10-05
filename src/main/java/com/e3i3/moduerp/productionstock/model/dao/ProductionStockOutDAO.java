package com.e3i3.moduerp.productionstock.model.dao;

import java.util.List;

import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

public interface ProductionStockOutDAO {
	List<ProductionStockOutDTO> selectAllProductionStockOuts();

	void insertProductionStockOut(ProductionStockOutDTO productionStockOutDTO);

	List<ProductionStockOutDTO> selectProductionStockOutByItemCode(String itemCode);

	ProductionStockOutDTO SelectProductionStockOutByPStockId(String pStockOutId);

	void updateProductionStockOut(ProductionStockOutDTO productionStockOutDTO);

	// itemCode에 따른 총 출고 수량 구하기
	int selectTotalStockOutByItemCode(String itemCode);

}
