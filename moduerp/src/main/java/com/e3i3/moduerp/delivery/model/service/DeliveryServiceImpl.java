package com.e3i3.moduerp.delivery.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.e3i3.moduerp.delivery.model.dao.DeliveryDAO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

public class DeliveryServiceImpl implements DeliveryService{
	
	@Autowired
	private DeliveryDAO deleveryDAO;

	@Override
	public List<ItemDTO> getAllItemsByBizNumber(String bizNumber) {
		return deleveryDAO.getAllItemsByBizNumber(bizNumber);
	}

	

}
