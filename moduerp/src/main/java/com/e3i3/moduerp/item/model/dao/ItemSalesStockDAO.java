package com.e3i3.moduerp.item.model.dao;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemSalesStockDAO {
    List<String> selectItemNamesByBizNumber(String bizNumber);

    List<String> selectStockPlacesByBizNumber(String bizNumber); // ���� �޼���

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


	}
