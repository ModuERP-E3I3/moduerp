package com.e3i3.moduerp.salesstock.service;

import java.util.List;

import com.e3i3.moduerp.salesstock.model.dto.SalesStockOutDTO;

public interface SalesStockOutService {
    List<SalesStockOutDTO> getAllSalesStockOuts();

    void insertSalesStockOut(SalesStockOutDTO salesStockOutDTO);

    List<SalesStockOutDTO> getSalesStockOutDetails(String itemCode);

    SalesStockOutDTO getSalesStockOutDetailsSub(String sStockOutId);

    void updateSalesStockOut(SalesStockOutDTO salesStockOutDTO);

    // itemCode에 따른 총 출고 수량 구하기
    int getTotalStockOutByItemCode(String itemCode);

    void deleteSalesStockOut(String sStockOutId);

    SalesStockOutDTO getMostRecentStockOutDetails(String itemCode);
}
