package com.e3i3.moduerp.item.model.service;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemProductionstockService {
	List<String> getItemNamesByBizNumber(String bizNumber);

	List<String> getStockPlacesByBizNumber(String bizNumber); // 추가된 메서드

	void insertItem(ItemDTO itemDTO);

	ItemDTO getItemDetails(String itemCode);

	// biz_number로 필터링된 아이템 리스트를 가져오는 메서드
	List<ItemDTO> getItemsByBizNumber(String bizNumber);

	void updateItem(ItemDTO itemDTO);

	void deleteItemByCode(String itemCode);

}