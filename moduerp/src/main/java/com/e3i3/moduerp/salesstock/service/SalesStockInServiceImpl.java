package com.e3i3.moduerp.salesstock.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
