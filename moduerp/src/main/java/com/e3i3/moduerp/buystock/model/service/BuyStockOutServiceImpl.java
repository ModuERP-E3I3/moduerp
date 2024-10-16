package com.e3i3.moduerp.buystock.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		BuyStockOutDao.insertBuyStockOut(buyStockOutDTO);
		
	}

	@Override
	public List<BuyStockOutDTO> getBuyStockOutDetails(String itemCode) {
		return BuyStockOutDao.selectBuyStockOutByItemCode(itemCode);
	}

	@Override
	public BuyStockOutDTO getBuyStockOutDetailssSub(String bStockOutId) {
		return BuyStockOutDao.SelectBuyStockOutByBStockId(bStockOutId);
	}

	@Override // DAO 호출하여 출고 정보 업데이트
	@Transactional
	public void updateBuyStockOut(BuyStockOutDTO buyStockOutDTO) {
		BuyStockOutDao.updateBuyStockOut(buyStockOutDTO);		
	}

	@Override
	public int getTotalStockOutByItemCode(String itemCode) {
		return BuyStockOutDao.selectTotalStockOutByItemCode(itemCode);
	}

	@Override
	public void deleteBuyStockOut(String bStockOutId) {
		BuyStockOutDao.deleteBuyStockOut(bStockOutId);
	}

	@Override
	public BuyStockOutDTO getMostRecentStockOutDetails(String itemCode) {
		return BuyStockOutDao.selectMostRecentStockOutDetails(itemCode);
	}
	
}
