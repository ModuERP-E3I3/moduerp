package com.e3i3.moduerp.item.model.service;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemProductionstockService {
    List<String> getItemNamesByBizNumber(String bizNumber);
    List<String> getStockPlacesByBizNumber(String bizNumber); // 추가된 메서드
    
    void insertItem(ItemDTO itemDTO);
}
