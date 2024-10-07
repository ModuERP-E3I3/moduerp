package com.e3i3.moduerp.salesstock.model.dao;

import java.util.List;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockInDTO;

public interface SalesStockInDAO {
    List<SalesStockInDTO> getAllSalesStockIn();  // 모든 판매 재고 정보를 조회

    void insertSalesStockIn(SalesStockInDTO salesStockInDTO);  // 새로운 판매 재고 정보를 삽입

    SalesStockInDTO selectSalesStockInByItemCode(String itemCode);  // 아이템 코드를 기반으로 특정 판매 재고 정보 조회

    void updateSalesStockIn(SalesStockInDTO salesStockInDTO);  // 기존 판매 재고 정보를 업데이트

    void deleteSalesStockInByItemCode(String itemCode);  // 아이템 코드를 기반으로 판매 재고 정보 삭제
}
