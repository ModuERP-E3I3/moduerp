package com.e3i3.moduerp.item.model.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;


public interface ItemSalesStockService {
    List<String> getSalesItemNamesByBizNumber(String bizNumber);

    List<String> getStockPlacesByBizNumber(String bizNumber);

    void insertItem(ItemDTO itemDTO);

    ItemDTO getItemDetails(String itemCode);

    List<ItemDTO> getItemsByBizNumber(String bizNumber);

    void updateItem(ItemDTO itemDTO);

    void deleteItemByCode(String itemCode);
    
	// ---------------------------------------------------
	// salesOUT
    
	List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber);

	List<ItemDTO> getItemsByBizNumberStartingWith(String bizNumber);

	void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut, double outPrice, String oDirector);

	// STOCK_OUT 업데이트
	void updateItemStockOutTotal(String itemCode, int totalStockOut);

	// STOCK_IN 값 가져오기
	int getStockInByItemCode(String itemCode);

	// STOCK 값 업데이트
	void updateItemStock(String itemCode, int updatedStock);

	public void updateItemCreatedOutAt(String itemCode, Timestamp createdOutAt);

	public void updateItemOutPrice(String itemCode, double outPrice);

	void updateItemWithLatestStockOut(String itemCode, Timestamp latestOutDate, double latestOutPrice,
			String latestOutPlace);

	void resetItemStockOutDetails(String itemCode);

	void resetItemStockOut(String itemCode);
	
	

	// -----------------------------------------------
	// sales in filter !!!

	List<ItemDTO> getItemByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate);

	List<ItemDTO> getItemsByFilter(String bizNumber, String option, String filterText);

	// -----------------------------------------------
	// sales Out filter
	List<ItemDTO> getItemOutByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate);

	List<ItemDTO> getItemOutByFilter(String bizNumber, String option, String filterText);

    
    
    
    
}
