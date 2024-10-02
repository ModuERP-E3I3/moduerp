package com.e3i3.moduerp.item.model.service;

import com.e3i3.moduerp.item.model.dao.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    @Override
    public List<String> getItemNamesByBizNumber(String bizNumber) {
        return itemDAO.selectItemNamesByBizNumber(bizNumber);
    }
}
