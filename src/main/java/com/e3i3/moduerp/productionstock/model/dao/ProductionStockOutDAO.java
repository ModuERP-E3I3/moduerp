package com.e3i3.moduerp.productionstock.model.dao;

import java.util.List;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

public interface ProductionStockOutDAO {
    List<ProductionStockOutDTO> selectAllProductionStockOuts();
}
