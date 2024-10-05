package com.e3i3.moduerp.buystock.model.dao;

import java.util.List;

import com.e3i3.moduerp.buystock.model.dto.BuyStockOutDTO;

public interface BuyStockOutDao {
	List<BuyStockOutDTO> selectAllBuyStockOuts();

}
