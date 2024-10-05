package com.e3i3.moduerp.item.model.dao;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;

public interface ItemProductionstockDAO {
	List<String> selectItemNamesByBizNumber(String bizNumber);

	List<String> selectStockPlacesByBizNumber(String bizNumber); // ���� �޼���

	// biz_number�� ���͸��� ������ ����Ʈ�� �������� �޼���
	List<ItemDTO> getItemsByBizNumber(String bizNumber);

	void insertItem(ItemDTO itemDTO);

	ItemDTO selectItemByCode(String itemCode);

	void updateItem(ItemDTO itemDTO);

	void deleteItemByCode(String itemCode);

	// ---------------------------------------------------
	// productionOUT
	List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber);

	List<ItemDTO> selectItemsByBizNumberStartingWith(String bizNumber);

	void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut, double outPrice,
			int updatedStock);

	int getStockByItemCode(String itemCode);

	// STOCK_OUT 업데이트
	void updateStockOutByItemCode(String itemCode, int totalStockOut);

	// STOCK_IN 값 가져오기
	int getStockInByItemCode(String itemCode);

	// STOCK 값 업데이트
	void updateStockByItemCode(String itemCode, int updatedStock);
}