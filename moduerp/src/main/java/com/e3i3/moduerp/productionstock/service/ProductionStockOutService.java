package com.e3i3.moduerp.productionstock.service;

import java.util.List;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

public interface ProductionStockOutService {
    List<ProductionStockOutDTO> getAllProductionStockOuts();
}
