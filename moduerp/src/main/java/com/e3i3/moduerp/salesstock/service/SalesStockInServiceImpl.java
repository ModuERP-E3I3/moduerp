package com.e3i3.moduerp.salesstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3i3.moduerp.salesstock.model.dao.SalesStockInDAO;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockInDTO;

@Service
public class SalesStockInServiceImpl implements SalesStockInService {

    @Autowired
    private SalesStockInDAO salesStockInDAO;

    @Override
    public List<SalesStockInDTO> getAllSalesStockIn() {
        return salesStockInDAO.getAllSalesStockIn();
    }

    @Override
    public void insertSalesStockIn(SalesStockInDTO salesStockInDTO) {
        salesStockInDAO.insertSalesStockIn(salesStockInDTO);
    }

    @Override
    public SalesStockInDTO getSalesStockInDetails(String itemCode) {
        return salesStockInDAO.selectSalesStockInByItemCode(itemCode);
    }

    @Override
    public void updateSalesStockIn(SalesStockInDTO salesStockInDTO) {
        salesStockInDAO.updateSalesStockIn(salesStockInDTO);
    }

    @Override
    public void deleteSalesStockInByItemCode(String itemCode) {
        salesStockInDAO.deleteSalesStockInByItemCode(itemCode);
    }
}
