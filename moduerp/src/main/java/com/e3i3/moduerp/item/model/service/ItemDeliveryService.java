package com.e3i3.moduerp.item.model.service;

import java.sql.Timestamp;
import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;


public interface ItemDeliveryService {
	
	List<String> getItemNamesByBizNumber(String bizNumber);

	List<String> getStockPlacesByBizNumber(String bizNumber); 

	void insertItem(ItemDTO itemDTO);

	ItemDTO getItemDetails(String itemCode);
	
	List<ItemDTO> getItemsByBizNumber(String bizNumber);

	void updateItem(ItemDTO itemDTO);

	void deleteItemByCode(String itemCode);
	
	List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber);

	List<ItemDTO> getItemsByBizNumberStartingWith(String bizNumber);

	void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut, double outPrice, String oDirector);
	
	void updateItemStockOutTotal(String itemCode, int totalStockOut);
	
	int getStockInByItemCode(String itemCode);
	
	void updateItemStock(String itemCode, int updatedStock);

	public void updateItemCreatedOutAt(String itemCode, Timestamp createdOutAt);

	public void updateItemOutPrice(String itemCode, double outPrice);

	void updateItemWithLatestStockOut(String itemCode, Timestamp latestOutDate, double latestOutPrice,
			String latestOutPlace);

	void resetItemStockOutDetails(String itemCode);

	void resetItemStockOut(String itemCode);
	
	//buy stock in filter

	List<ItemDTO> getItemByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate);

	List<ItemDTO> getItemsByFilter(String bizNumber, String option, String filterText);
	
	//buy stock out filter
	
	List<ItemDTO> getItemOutByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate);

	List<ItemDTO> getItemOutByFilter(String bizNumber, String option, String filterText);

	List<ItemDTO> getItemByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<ItemDTO> getItemByFilterStartDate(String bizNumber, String startDate);

	List<ItemDTO> getItemByFilterEndDate(String bizNumber, String endDate);
	
}
