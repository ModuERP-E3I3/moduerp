package com.e3i3.moduerp.buystock.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.e3i3.moduerp.buystock.DAO.BuyStockDao;
import com.e3i3.moduerp.buystock.DTO.BuyStockInDto;
import com.e3i3.moduerp.buystock.DTO.BuyStockOutDto;

public class BuyStockService {

    @Autowired
    private BuyStockDao buyStockDAO;

    // 备概 涝绊 贸府 肺流
    public boolean handleBuyStockIn(BuyStockInDto buyStockInDto) {
        return buyStockDAO.insertBuyStockIn(buyStockInDto);
    }

    // 备概 免绊 贸府 肺流
    public boolean handleBuyStockOut(BuyStockOutDto buyStockOutDto) {
        return buyStockDAO.insertBuyStockOut(buyStockOutDto);
    }
    
}
