package com.e3i3.moduerp.delivery.model.dao;

import java.util.List;

import com.e3i3.moduerp.delivery.model.dto.DeliveryDTO;

public interface DeliveryDAO {

	List<DeliveryDTO> getAllDeliveryTableList();

	List<DeliveryDTO> getAllDelivery();

	void insertDelivery(DeliveryDTO deliveryDTO);

	DeliveryDTO selectDeliveryByItemCode(String itemCode);

	void updateDelivery(DeliveryDTO deliveryDTO);

	void deleteDeliveryByItemCode(String itemCode);

	List<String> getItemItemCode(String itemCode);
}
