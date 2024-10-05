package com.e3i3.moduerp.item.model.dao;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemProductionstockDAO {
	List<String> selectItemNamesByBizNumber(String bizNumber);

	List<String> selectStockPlacesByBizNumber(String bizNumber); 

	
	List<ItemDTO> getItemsByBizNumber(String bizNumber);

	void insertItem(ItemDTO itemDTO);

	ItemDTO selectItemByCode(String itemCode);

	void updateItem(ItemDTO itemDTO);

	void deleteItemByCode(String itemCode);
}
