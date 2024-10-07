package com.e3i3.moduerp.salesstock.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
