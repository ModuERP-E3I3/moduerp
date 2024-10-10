package com.e3i3.moduerp.salesstock.model.dao;

import java.util.List;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockOutDTO;

public interface SalesStockOutDAO {
    List<SalesStockOutDTO> selectAllSalesStockOuts();
}
