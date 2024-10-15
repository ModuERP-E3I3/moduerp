package com.e3i3.moduerp.item.model.dao;

import java.sql.Timestamp;
import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemSalesStockDAO {
	List<String> selectSalesItemNamesByBizNumber(String bizNumber);

	List<String> selectStockPlacesByBizNumber(String bizNumber);

	// biz_number�� �Ǹ� �׸��� �������� �޼ҵ�
	List<ItemDTO> getItemsByBizNumber(String bizNumber);

	void insertItem(ItemDTO itemDTO);

	ItemDTO selectItemByCode(String itemCode);

	void updateItem(ItemDTO itemDTO);

	void deleteItemByCode(String itemCode);

	// ---------------------------------------------------
	// salesOUT
	List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber);

	List<ItemDTO> selectItemsByBizNumberStartingWith(String bizNumber);

	void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut, double outPrice,
			int updatedStock, String oDirector);

	int getStockByItemCode(String itemCode);

	// STOCK_OUT ������Ʈ
	void updateStockOutByItemCode(String itemCode, int totalStockOut);

	// STOCK_IN �� ��������
	int getStockInByItemCode(String itemCode);

	// STOCK ������Ʈ
	void updateStockByItemCode(String itemCode, int updatedStock);

	public void updateItemCreatedOutAt(String itemCode, Timestamp createdOutAt);

	public void updateItemOutPrice(String itemCode, double outPrice);

	void updateItemWithLatestStockOut(String itemCode, Timestamp latestOutDate, double latestOutPrice,
			String latestOutPlace);

	void resetItemStockOutDetails(String itemCode);

	void updateItemStockOutToNull(String itemCode);

	
	
	// -----------------------------------------------
	// sales in filter !!!!

	// 필터링된 항목 가져오는 메서드
	List<ItemDTO> getItemByItemNameDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemByStockPlaceDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemByiDirectorDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemByItemName(String bizNumber, String filterText);

	List<ItemDTO> getItemByStockPlace(String bizNumber, String filterText);

	List<ItemDTO> getItemByiDirector(String bizNumber, String filterText);
	
	
	List<ItemDTO> getItemByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<ItemDTO> getItemByFilterStartDate(String bizNumber, String startDate);

	List<ItemDTO> getItemByFilterEndDate(String bizNumber, String endDate);

	// -----------------------------------------------
	// sales out filter   !!!!
	List<ItemDTO> getItemOutByItemNameDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemOutByStockOutPlaceDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getItemOutByODirectorDate(String bizNumber, String filterText, String startDate, String endDate);

	List<ItemDTO> getOutItemByItemName(String bizNumber, String filterText);

	List<ItemDTO> getOutItemByStockOutPlace(String bizNumber, String filterText);

	List<ItemDTO> getOutItemByODirector(String bizNumber, String filterText);

	
	List<ItemDTO> getItemOutByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<ItemDTO> getItemOutByFilterStartDate(String bizNumber, String startDate);

	List<ItemDTO> getItemOutByFilterEndDate(String bizNumber, String endDate);



}