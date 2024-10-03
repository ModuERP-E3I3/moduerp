package com.e3i3.moduerp.productionstock.service;

import java.util.List;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;

public interface ProductionStockInService {
	List<ProductionStockInDTO> getAllProductionStockIn();

	void insertProductionStockIn(ProductionStockInDTO productionStockInDTO);

	ProductionStockInDTO getProductionStockInDetails(String itemCode);

	void updateProductionStockIn(ProductionStockInDTO productionStockInDTO);

	void deleteProductionStockInByItemCode(String itemCode);

}
