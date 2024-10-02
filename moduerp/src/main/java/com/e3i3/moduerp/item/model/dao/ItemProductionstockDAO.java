package com.e3i3.moduerp.item.model.dao;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;

public interface ItemProductionstockDAO {
    List<String> selectItemNamesByBizNumber(String bizNumber);
    List<String> selectStockPlacesByBizNumber(String bizNumber); // ���� �޼���

    
    void insertItem(ItemDTO itemDTO);
}
