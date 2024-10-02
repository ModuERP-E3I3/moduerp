package com.e3i3.moduerp.item.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.item.model.dao.ItemProductionstockDAO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

@Service
public class ItemProductionstockServiceImpl implements ItemProductionstockService {

	@Autowired
    private ItemProductionstockDAO itemProductionstockDAO;

    @Override
    public List<String> getItemNamesByBizNumber(String bizNumber) {
        return itemProductionstockDAO.selectItemNamesByBizNumber(bizNumber);
    }

    @Override
    public List<String> getStockPlacesByBizNumber(String bizNumber) { // 추가된 메서드
        return itemProductionstockDAO.selectStockPlacesByBizNumber(bizNumber);
    }
    
    @Override
    public void insertItem(ItemDTO itemDTO) {
        itemProductionstockDAO.insertItem(itemDTO);
    }
}
