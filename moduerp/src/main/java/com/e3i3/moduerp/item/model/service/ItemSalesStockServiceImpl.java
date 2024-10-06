package com.e3i3.moduerp.item.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.item.model.dao.ItemSalesStockDAO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

@Service
public class ItemSalesStockServiceImpl implements ItemSalesStockService {

    @Autowired
    private ItemSalesStockDAO itemSalesStockDAO;

    @Override
    public List<String> getItemNamesByBizNumber(String bizNumber) {
        return itemSalesStockDAO.selectItemNamesByBizNumber(bizNumber);
    }

    @Override
    public List<String> getStockPlacesByBizNumber(String bizNumber) { // 추가된 메서드
        return itemSalesStockDAO.selectStockPlacesByBizNumber(bizNumber);
    }

    @Override
    public void insertItem(ItemDTO itemDTO) {
        itemSalesStockDAO.insertItem(itemDTO);
    }

    @Override
    public List<ItemDTO> getItemsByBizNumber(String bizNumber) {
        return itemSalesStockDAO.getItemsByBizNumber(bizNumber);
    }

    @Override
    public ItemDTO getItemDetails(String itemCode) {
        return itemSalesStockDAO.selectItemByCode(itemCode);
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        itemSalesStockDAO.updateItem(itemDTO);
    }

    @Override
    public void deleteItemByCode(String itemCode) {
        itemSalesStockDAO.deleteItemByCode(itemCode);
    }
}
