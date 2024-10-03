package com.e3i3.moduerp.productionstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3i3.moduerp.productionstock.model.dao.ProductionStockInDAO;
import com.e3i3.moduerp.item.model.dao.ItemProductionstockDAO;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;

@Service
public class ProductionStockInServiceImpl implements ProductionStockInService {

	@Autowired
	private ProductionStockInDAO productionStockInDAO;

//    @Autowired
//    private ItemProductionstockDAO itemProductionstockDAO; 

	@Override
	public List<ProductionStockInDTO> getAllProductionStockIn() {
		return productionStockInDAO.getAllProductionStockIn();
	}

	@Override
	public void insertProductionStockIn(ProductionStockInDTO productionStockInDTO) {
		productionStockInDAO.insertProductionStockIn(productionStockInDTO);
	}

	@Override
	public ProductionStockInDTO getProductionStockInDetails(String itemCode) {
		return productionStockInDAO.selectProductionStockInByItemCode(itemCode);
	}

	@Override
	public void updateProductionStockIn(ProductionStockInDTO productionStockInDTO) {
		productionStockInDAO.updateProductionStockIn(productionStockInDTO);
	}
}
