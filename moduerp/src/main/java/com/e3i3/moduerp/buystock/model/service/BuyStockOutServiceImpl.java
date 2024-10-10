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

	@Override
	public void insertBuyStockOut(BuyStockOutDTO buyStockOutDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BuyStockOutDTO> getBuyStockOutDetails(String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuyStockOutDTO getBuyStockOutDetailssSub(String pStockOutId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBuyStockOut(BuyStockOutDTO buyStockOutDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalStockOutByItemCode(String itemCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteBuyStockOut(String pStockOutId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BuyStockOutDTO getMostRecentStockOutDetails(String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
