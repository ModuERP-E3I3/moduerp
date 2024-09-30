package com.e3i3.moduerp.productionstock.dao;

import java.util.List;
import com.e3i3.moduerp.productionstock.dto.ProductionStockInDTO;

public interface ProductionStockInDAO {
    List<ProductionStockInDTO> getAllProductionStockIn();
}