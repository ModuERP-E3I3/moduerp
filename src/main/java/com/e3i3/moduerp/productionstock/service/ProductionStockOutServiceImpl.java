package com.e3i3.moduerp.productionstock.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.productionstock.model.dao.ProductionStockOutDAO;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

@Service
public class ProductionStockOutServiceImpl implements ProductionStockOutService {

    @Autowired
    private ProductionStockOutDAO productionStockOutDAO;

    @Override
    public List<ProductionStockOutDTO> getAllProductionStockOuts() {
        return productionStockOutDAO.selectAllProductionStockOuts();
    }
}
