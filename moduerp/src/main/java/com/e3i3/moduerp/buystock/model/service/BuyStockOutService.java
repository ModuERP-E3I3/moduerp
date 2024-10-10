package com.e3i3.moduerp.buystock.model.service;

import java.util.List;

import com.e3i3.moduerp.buystock.model.dto.BuyStockOutDTO;

public interface BuyStockOutService {
	List<BuyStockOutDTO> getAllBuyStockOuts();

	void insertBuyStockOut(BuyStockOutDTO buyStockOutDTO);

	List<BuyStockOutDTO> getBuyStockOutDetails(String itemCode);

	BuyStockOutDTO getBuyStockOutDetailssSub(String pStockOutId);

	void updateBuyStockOut(BuyStockOutDTO buyStockOutDTO);

	// itemCode에 따른 총 출고 수량 구하기
	int getTotalStockOutByItemCode(String itemCode);

	void deleteBuyStockOut(String pStockOutId);

	BuyStockOutDTO getMostRecentStockOutDetails(String itemCode);

}
