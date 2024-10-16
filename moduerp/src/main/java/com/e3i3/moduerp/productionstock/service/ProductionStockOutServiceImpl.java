package com.e3i3.moduerp.productionstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3i3.moduerp.productionstock.model.dao.ProductionStockOutDAO;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

@Service
public class ProductionStockOutServiceImpl implements ProductionStockOutService {

	@Autowired
	private ProductionStockOutDAO productionStockOutDAO;

	@Override
	public List<ProductionStockOutDTO> getAllProductionStockOuts() {
		return productionStockOutDAO.selectAllProductionStockOuts();
	}

	@Override
	public void insertProductionStockOut(ProductionStockOutDTO productionStockOutDTO) {
		productionStockOutDAO.insertProductionStockOut(productionStockOutDTO);
	}

	@Override
	public List<ProductionStockOutDTO> getProductionStockOutDetails(String itemCode) {
		return productionStockOutDAO.selectProductionStockOutByItemCode(itemCode);
	}

	@Override
	public ProductionStockOutDTO getProductionStockOutDetailssSub(String pStockOutId) {
		return productionStockOutDAO.SelectProductionStockOutByPStockId(pStockOutId);
	}

	@Override
	@Transactional
	public void updateProductionStockOut(ProductionStockOutDTO productionStockOutDTO) {
		// DAO 호출하여 출고 정보 업데이트
		productionStockOutDAO.updateProductionStockOut(productionStockOutDTO);
	}

	@Override
	public int getTotalStockOutByItemCode(String itemCode) {
		return productionStockOutDAO.selectTotalStockOutByItemCode(itemCode);
	}

	@Override
	public void deleteProductionStockOut(String pStockOutId) {
		productionStockOutDAO.deleteProductionStockOut(pStockOutId);
	}

	@Override
	public ProductionStockOutDTO getMostRecentStockOutDetails(String itemCode) {
		return productionStockOutDAO.selectMostRecentStockOutDetails(itemCode);
	}
}
