package com.e3i3.moduerp.salesstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3i3.moduerp.salesstock.model.dao.SalesStockOutDAO;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockOutDTO;

@Service
public class SalesStockOutServiceImpl implements SalesStockOutService {

    @Autowired
    private SalesStockOutDAO salesStockOutDAO;

    @Override
    public List<SalesStockOutDTO> getAllSalesStockOuts() {
        return salesStockOutDAO.selectAllSalesStockOuts();
    }

    @Override
    public void insertSalesStockOut(SalesStockOutDTO salesStockOutDTO) {
        salesStockOutDAO.insertSalesStockOut(salesStockOutDTO);
    }

    @Override
    public List<SalesStockOutDTO> getSalesStockOutDetails(String itemCode) {
        return salesStockOutDAO.selectSalesStockOutByItemCode(itemCode);
    }

    @Override
    public SalesStockOutDTO getSalesStockOutDetailsSub(String sStockOutId) {
        return salesStockOutDAO.selectSalesStockOutBySStockId(sStockOutId);
    }

    @Override
    @Transactional
    public void updateSalesStockOut(SalesStockOutDTO salesStockOutDTO) {
        // DAO 호출하여 출고 정보 업데이트
        salesStockOutDAO.updateSalesStockOut(salesStockOutDTO);
    }

    @Override
    public int getTotalStockOutByItemCode(String itemCode) {
        return salesStockOutDAO.selectTotalStockOutByItemCode(itemCode);
    }

    @Override
    public void deleteSalesStockOut(String sStockOutId) {
        salesStockOutDAO.deleteSalesStockOut(sStockOutId);
    }

    @Override
    public SalesStockOutDTO getMostRecentStockOutDetails(String itemCode) {
        return salesStockOutDAO.selectMostRecentStockOutDetails(itemCode);
    }
}
