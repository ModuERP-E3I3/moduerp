package com.e3i3.moduerp.productionstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.productionstock.model.dao.ProductionStockInDAO;
import com.e3i3.moduerp.item.model.dao.ItemProductionstockDAO; // 추가
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;

@Service
public class ProductionStockInServiceImpl implements ProductionStockInService {

    @Autowired
    private ProductionStockInDAO productionStockInDAO;

    @Autowired
    private ItemProductionstockDAO itemProductionstockDAO; // 추가

    @Override
    public List<ProductionStockInDTO> getAllProductionStockIn() {
        return productionStockInDAO.getAllProductionStockIn();
    }

    
    
}
