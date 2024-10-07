package com.e3i3.moduerp.buystock.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.buystock.model.dao.BuyStockInDao;
import com.e3i3.moduerp.buystock.model.dto.BuyStockInDTO;

@Service
public class BuyStockInServiceImpl implements BuyStockInService {

    @Autowired
    private BuyStockInDao buyStockInDao; 

    @Override
    public List<BuyStockInDTO> getAllBuyStockIn() {
        return buyStockInDao.getAllBuyStockIn();
    }

	@Override
	public void insertBuyStockIn(BuyStockInDTO buyStockInDTO) {
		buyStockInDao.insertBuyStockIn(buyStockInDTO);
		
	}

	@Override
	public BuyStockInDTO getBuyStockInDetails(String itemCode) {
		return buyStockInDao.selectBuyStockInByItemCode(itemCode);
	}

	@Override
	public void updateBuyStockIn(BuyStockInDTO buyStockInDTO) {
		buyStockInDao.updateBuyStockIn(buyStockInDTO);
		
	}

	@Override
	public void deleteBuyStockInByItemCode(String itemCode) {
		buyStockInDao.selectBuyStockInByItemCode(itemCode);
		
	}
}
