package com.e3i3.moduerp.delivery.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.delivery.model.dao.DeliveryDAO;
import com.e3i3.moduerp.delivery.model.dto.DeliveryDTO;


@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryDAO deliveryDAO;

	@Override
	public List<DeliveryDTO> getAllDelivery() {
		return deliveryDAO.getAllDelivery();
	}

	@Override
	public void insertDelivery(DeliveryDTO deliveryDTO) {
		deliveryDAO.insertDelivery(deliveryDTO);
	}

	@Override
	public DeliveryDTO getDeliveryDetails(String itemCode) {
		return deliveryDAO.selectDeliveryByItemCode(itemCode);
	}

	@Override
	public void updateDelivery(DeliveryDTO deliveryDTO) {
		deliveryDAO.updateDelivery(deliveryDTO);
		
	}

	@Override
	public void deleteDeliveryByItemCode(String itemCode) {
		deliveryDAO.selectDeliveryByItemCode(itemCode);
	}

	@Override
	public List<DeliveryDTO> getAllDelivery(String bizNumber) {
		return deliveryDAO.getAllDeliveryTableList();
	}

	@Override
	public List<String> getItemItemCode(String bizNumber) {
		return deliveryDAO.getItemItemCode(bizNumber);
	}

	@Override
	public DeliveryDTO getDeliveryDetailsSub(String deliveryId) {
		return deliveryDAO.SelectDeliveryByDelivery(deliveryId);
	}

	@Override
	public DeliveryDTO getItemDetails(String itemCode) {
		return deliveryDAO.selectDeliveryByItemCode(itemCode);
	}





}
