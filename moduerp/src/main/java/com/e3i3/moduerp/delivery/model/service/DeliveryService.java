package com.e3i3.moduerp.delivery.model.service;

import java.util.List;

import com.e3i3.moduerp.delivery.model.dto.DeliveryDTO;


public interface DeliveryService {
	 List<DeliveryDTO> getAllDelivery();
	 
	 void insertDelivery(DeliveryDTO deliveryDTO);

	 DeliveryDTO getDeliveryDetails(String itemCode);

	 void updateDelivery(DeliveryDTO deliveryDTO);

	 void deleteDeliveryByItemCode(String itemCode);

	List<DeliveryDTO> getAllDelivery(String bizNumber);

}
