
package com.e3i3.moduerp.productionstock.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.productionstock.dao.ProductionStockInDAO;
import com.e3i3.moduerp.productionstock.dto.ProductionStockInDTO;

@Service
public class ProductionStockInServiceImpl implements ProductionStockInService {

    @Autowired
    private ProductionStockInDAO productionStockInDAO;

    @Override
    public List<ProductionStockInDTO> getAllProductionStockIn() {
        return productionStockInDAO.getAllProductionStockIn();
    }
}
