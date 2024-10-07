package com.e3i3.moduerp.buystock.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.buystock.model.dao.BuyStockOutDao;
import com.e3i3.moduerp.buystock.model.dto.BuyStockOutDTO;



@Service
public class BuyStockOutServiceImpl implements BuyStockOutService{

	@Autowired
    private BuyStockOutDao BuyStockOutDao;

    @Override
    public List<BuyStockOutDTO> getAllBuyStockOuts() {
        return BuyStockOutDao.selectAllBuyStockOuts();
    }
	
}
