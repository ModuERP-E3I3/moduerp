package com.e3i3.moduerp.salesstock.service;

import java.util.List;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockInDTO;

public interface SalesStockInService {
    List<SalesStockInDTO> getAllSalesStockIn();

    void insertSalesStockIn(SalesStockInDTO salesStockInDTO);

    SalesStockInDTO getSalesStockInDetails(String itemCode);

    void updateSalesStockIn(SalesStockInDTO salesStockInDTO);

    void deleteSalesStockInByItemCode(String itemCode);
}
