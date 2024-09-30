package com.e3i3.moduerp.buystock.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.e3i3.moduerp.buystock.DAO.BuyStockDao;
import com.e3i3.moduerp.buystock.DTO.BuyStockInDto;
import com.e3i3.moduerp.buystock.DTO.BuyStockOutDto;

public class BuyStockService {

    @Autowired
    private BuyStockDao buyStockDAO;

    // ���� �԰� ó�� ����
    public boolean handleBuyStockIn(BuyStockInDto buyStockInDto) {
        return buyStockDAO.insertBuyStockIn(buyStockInDto);
    }

    // ���� ��� ó�� ����
    public boolean handleBuyStockOut(BuyStockOutDto buyStockOutDto) {
        return buyStockDAO.insertBuyStockOut(buyStockOutDto);
    }
    
}
