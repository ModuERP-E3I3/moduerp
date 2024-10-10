package com.e3i3.moduerp.salesstock.model.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.e3i3.moduerp.salesstock.model.dto.SalesStockOutDTO;

public interface SalesStockOutDAO {
    List<SalesStockOutDTO> selectAllSalesStockOuts();

    void insertSalesStockOut(SalesStockOutDTO salesStockOutDTO);

    List<SalesStockOutDTO> selectSalesStockOutByItemCode(String itemCode);

    SalesStockOutDTO selectSalesStockOutBySStockId(String sStockOutId);

    void updateSalesStockOut(SalesStockOutDTO salesStockOutDTO);

    // itemCode에 따른 총 출고 수량 구하기
    int selectTotalStockOutByItemCode(String itemCode);

    void deleteSalesStockOut(String sStockOutId);

    public Timestamp getLatestStockOutDateByItemCode(String itemCode);

    public double getLatestOutPriceByItemCode(String itemCode);

    Map<String, Object> getLatestOutPlaceAndPriceByItemCode(String itemCode);

    void updateOutPlaceAndPriceInItem(String itemCode, Map<String, Object> data);

    SalesStockOutDTO selectMostRecentStockOutDetails(String itemCode);
}
