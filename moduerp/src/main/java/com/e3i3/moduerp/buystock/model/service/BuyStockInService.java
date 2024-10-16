package com.e3i3.moduerp.buystock.model.service;

import java.util.List;

import com.e3i3.moduerp.buystock.model.dto.BuyStockInDTO;


public interface BuyStockInService {
	 List<BuyStockInDTO> getAllBuyStockIn();
	 
	 void insertBuyStockIn(BuyStockInDTO buyStockInDTO);

	 BuyStockInDTO getBuyStockInDetails(String itemCode);

	 void updateBuyStockIn(BuyStockInDTO buyStockInDTO);

	 void deleteBuyStockInByItemCode(String itemCode);
}
