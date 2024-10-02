package com.e3i3.moduerp.item.model.dao;

import java.util.List;

public interface ItemProductionstockDAO {
    List<String> selectItemNamesByBizNumber(String bizNumber);
    List<String> selectStockPlacesByBizNumber(String bizNumber); // 추가된 메서드
}
