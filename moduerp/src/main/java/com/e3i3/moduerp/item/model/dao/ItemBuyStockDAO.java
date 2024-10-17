package com.e3i3.moduerp.item.model.dao;

import java.sql.Timestamp;
import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemBuyStockDAO {

	List<String> selectItemNamesByBizNumber(String bizNumber);

	List<String> selectStockPlacesByBizNumber(String bizNumber);

	void insertItem(ItemDTO itemDTO);

	List<ItemDTO> getItemsByBizNumber(String bizNumber);

	ItemDTO selectItemByCode(String itemCode);

	void updateItem(ItemDTO itemDTO);

	void deleteItemByCode(String itemCode);

	List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber);

	List<ItemDTO> selectItemsByBizNumberStartingWith(String bizNumber);

	int getStockByItemCode(String itemCode);



	void updateStockOutByItemCode(String itemCode, int totalStockOut);

	int getStockInByItemCode(String itemCode);

	void updateStockByItemCode(String itemCode, int updatedStock);

	void updateItemCreatedOutAt(String itemCode, Timestamp createdOutAt);

	void updateItemOutPrice(String itemCode, double outPrice);


	void resetItemStockOutDetails(String itemCode);

	void updateItemStockOutToNull(String itemCode);


	void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut, double outPrice,
			int updatedStock, String oDirector);
	
	void updateItemWithLatestStockOut(String itemCode, Timestamp latestOutDate, double latestOutPrice,
			String latestOutPlace);
	
	// in filter
	
	List<ItemDTO> getItemByItemNameDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemByStockPlaceDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemByiDirectorDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemByItemName(String bizNumber, String filterText);

	List<ItemDTO> getItemByStockPlace(String bizNumber, String filterText);

	List<ItemDTO> getItemByiDirector(String bizNumber, String filterText);
	
	// -----------------------------------------------
	// filter
	List<ItemDTO> getItemOutByItemNameDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemOutByStockOutPlaceDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemOutByODirectorDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getOutItemByItemName(String bizNumber, String filterText);

	List<ItemDTO> getOutItemByStockOutPlace(String bizNumber, String filterText);

	List<ItemDTO> getOutItemByODirector(String bizNumber, String filterText);

	List<ItemDTO> getItemByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<ItemDTO> getItemByFilterStartDate(String bizNumber, String startDate);

	List<ItemDTO> getItemByFilterEndDate(String bizNumber, String endDate);

}
