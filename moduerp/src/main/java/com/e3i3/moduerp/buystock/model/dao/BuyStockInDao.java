package com.e3i3.moduerp.buystock.model.dao;

import java.util.List;

import com.e3i3.moduerp.buystock.model.dto.BuyStockInDTO;

public interface BuyStockInDao {

	List<BuyStockInDTO> getAllBuyStockIn();

	void insertBuyStockIn(BuyStockInDTO buyStockInDTO);

	BuyStockInDTO selectBuyStockInByItemCode(String itemCode);

	void updateBuyStockIn(BuyStockInDTO buyStockInDTO);

	void deleteBuyStockInByItemCode(String itemCode);


}
