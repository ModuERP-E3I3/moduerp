package com.e3i3.moduerp.delivery.model.dao;

import java.util.List;

import com.e3i3.moduerp.delivery.model.dto.DeliveryDTO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface DeliveryDAO {

	List<ItemDTO> getAllItemsByBizNumber(String bizNumber);

}
