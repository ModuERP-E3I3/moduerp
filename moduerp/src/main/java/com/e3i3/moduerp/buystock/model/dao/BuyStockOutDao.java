package com.e3i3.moduerp.buystock.model.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.e3i3.moduerp.buystock.model.dto.BuyStockOutDTO;


public interface BuyStockOutDao {
	List<BuyStockOutDTO> selectAllBuyStockOuts();

	void insertBuyStockOut(BuyStockOutDTO buyStockOutDTO);

	List<BuyStockOutDTO> selectBuyStockOutByItemCode(String itemCode);

	BuyStockOutDTO SelectBuyStockOutByBStockId(String bStockOutId);

	void updateBuyStockOut(BuyStockOutDTO buyStockOutDTO);
	
	//itemCode

	int selectTotalStockOutByItemCode(String itemCode);

	void deleteBuyStockOut(String bStockOutId);

	public Timestamp getLatestStockOutDateByItemCode(String itemCode);

	public double getLatestOutPriceByItemCode(String itemCode);

	Map<String, Object> getLatestOutPlaceAndPriceByItemCode(String itemCode);

	void updateOutPlaceAndPriceInItem(String itemCode, Map<String, Object> data);

	BuyStockOutDTO selectMostRecentStockOutDetails(String itemCode);

}
