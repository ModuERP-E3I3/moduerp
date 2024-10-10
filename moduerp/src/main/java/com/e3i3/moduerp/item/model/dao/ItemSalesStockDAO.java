package com.e3i3.moduerp.item.model.dao;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemSalesStockDAO {
    List<String> selectItemNamesByBizNumber(String bizNumber);

    List<String> selectStockPlacesByBizNumber(String bizNumber); // 기존 메서드

    // biz_number로 필터링된 아이템 리스트를 가져오는 메서드
    List<ItemDTO> getItemsByBizNumber(String bizNumber);

    void insertItem(ItemDTO itemDTO);

    ItemDTO selectItemByCode(String itemCode);

    void updateItem(ItemDTO itemDTO);

    void deleteItemByCode(String itemCode);
}
