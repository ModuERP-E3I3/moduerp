package com.e3i3.moduerp.item.model.dao;

import java.util.List;

public interface ItemDAO {
    List<String> selectItemNamesByBizNumber(String bizNumber);
}
