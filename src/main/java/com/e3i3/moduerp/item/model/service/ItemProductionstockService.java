package com.e3i3.moduerp.item.model.service;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemProductionstockService {
	List<String> getItemNamesByBizNumber(String bizNumber);

	List<String> getStockPlacesByBizNumber(String bizNumber); // �߰��� �޼���

	void insertItem(ItemDTO itemDTO);

	ItemDTO getItemDetails(String itemCode);

	// biz_number�� ���͸��� ������ ����Ʈ�� �������� �޼���
	List<ItemDTO> getItemsByBizNumber(String bizNumber);

	void updateItem(ItemDTO itemDTO);

	void deleteItemByCode(String itemCode);

	// ---------------------------------------------------
	// productionOUT
	List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber);

	List<ItemDTO> getItemsByBizNumberStartingWith(String bizNumber);

	void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut, double outPrice);

	// STOCK_OUT 업데이트
    void updateItemStockOutTotal(String itemCode, int totalStockOut);

    // STOCK_IN 값 가져오기
    int getStockInByItemCode(String itemCode);

    // STOCK 값 업데이트
    void updateItemStock(String itemCode, int updatedStock);
}