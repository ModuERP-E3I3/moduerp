package com.e3i3.moduerp.productionstock.model.dao;

import java.util.List;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;

public interface ProductionStockInDAO {
	List<ProductionStockInDTO> getAllProductionStockIn();

	void insertProductionStockIn(ProductionStockInDTO productionStockInDTO);

	ProductionStockInDTO selectProductionStockInByItemCode(String itemCode);

	void updateProductionStockIn(ProductionStockInDTO productionStockInDTO);

	void deleteProductionStockInByItemCode(String itemCode);
	
	
}
