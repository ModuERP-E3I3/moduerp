package com.e3i3.moduerp.item.model.service;

import com.e3i3.moduerp.item.model.dao.ItemProductionstockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemProductionstockServiceImpl implements ItemProductionstockService {

    @Autowired
    private ItemProductionstockDAO itemDAO;

    @Override
    public List<String> getItemNamesByBizNumber(String bizNumber) {
        return itemDAO.selectItemNamesByBizNumber(bizNumber);
    }

    @Override
    public List<String> getStockPlacesByBizNumber(String bizNumber) { // 추가된 메서드
        return itemDAO.selectStockPlacesByBizNumber(bizNumber);
    }
}
