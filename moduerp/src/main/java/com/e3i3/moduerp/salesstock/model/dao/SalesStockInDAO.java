package com.e3i3.moduerp.salesstock.model.dao;

import java.util.List;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockInDTO;

public interface SalesStockInDAO {
    List<SalesStockInDTO> getAllSalesStockIn();  // ��� �Ǹ� ��� ������ ��ȸ

    void insertSalesStockIn(SalesStockInDTO salesStockInDTO);  // ���ο� �Ǹ� ��� ������ ����

    SalesStockInDTO selectSalesStockInByItemCode(String itemCode);  // ������ �ڵ带 ������� Ư�� �Ǹ� ��� ���� ��ȸ

    void updateSalesStockIn(SalesStockInDTO salesStockInDTO);  // ���� �Ǹ� ��� ������ ������Ʈ

    void deleteSalesStockInByItemCode(String itemCode);  // ������ �ڵ带 ������� �Ǹ� ��� ���� ����
}
