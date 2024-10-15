package com.e3i3.moduerp.item.model.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.item.model.dao.ItemDeliveryDAO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

@Service
public class ItemDeliveryServiceImpl implements ItemDeliveryService {

	@Autowired
	private ItemDeliveryDAO itemDeliveryDAO;

	@Override
	public List<String> getItemNamesByBizNumber(String bizNumber) {
		return itemDeliveryDAO.selectItemNamesByBizNumber(bizNumber);
	}

	@Override
	public List<String> getStockPlacesByBizNumber(String bizNumber) { 
		return itemDeliveryDAO.selectStockPlacesByBizNumber(bizNumber);
	}

	@Override
	public void insertItem(ItemDTO itemDTO) {
		itemDeliveryDAO.insertItem(itemDTO);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumber(String bizNumber) {
		return itemDeliveryDAO.getItemsByBizNumber(bizNumber);
	}

	@Override
	public ItemDTO getItemDetails(String itemCode) {
		return itemDeliveryDAO.selectItemByCode(itemCode);
	}

	@Override
	public void updateItem(ItemDTO itemDTO) {
		itemDeliveryDAO.updateItem(itemDTO);
	}

	@Override
	public void deleteItemByCode(String itemCode) {
		itemDeliveryDAO.deleteItemByCode(itemCode);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber) {
		return itemDeliveryDAO.getItemsByBizNumberOutDate(bizNumber);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumberStartingWith(String bizNumber) {
		return itemDeliveryDAO.selectItemsByBizNumberStartingWith(bizNumber);
	}

	@Override
	public void updateItemStockOut(String itemCode, String createdOutAt, String StockOutPlace, int StockOut,
			double outPrice, String oDirector) {

		Integer currentStock = itemDeliveryDAO.getStockByItemCode(itemCode);

		int updatedStock = currentStock - StockOut;

		System.out.println("Updating item Stock: " + updatedStock);
		itemDeliveryDAO.updateItemStockOut(itemCode, createdOutAt, StockOutPlace, StockOut, outPrice,
				updatedStock, oDirector);
	}

	@Override
	public void updateItemStockOutTotal(String itemCode, int totalStockOut) {
		itemDeliveryDAO.updateStockOutByItemCode(itemCode, totalStockOut);
	}

	@Override
	public int getStockInByItemCode(String itemCode) {
		return itemDeliveryDAO.getStockInByItemCode(itemCode);
	}

	@Override
	public void updateItemStock(String itemCode, int updatedStock) {
		itemDeliveryDAO.updateStockByItemCode(itemCode, updatedStock);
	}

	@Override
	public void updateItemCreatedOutAt(String itemCode, Timestamp createdOutAt) {
		itemDeliveryDAO.updateItemCreatedOutAt(itemCode, createdOutAt);
	}

	@Override
	public void updateItemOutPrice(String itemCode, double outPrice) {
		itemDeliveryDAO.updateItemOutPrice(itemCode, outPrice);
	}

	@Override
	public void updateItemWithLatestStockOut(String itemCode, Timestamp latestOutDate, double latestOutPrice,
			String latestOutPlace) {
		itemDeliveryDAO.updateItemWithLatestStockOut(itemCode, latestOutDate, latestOutPrice, latestOutPlace);
	}

	@Override
	public void resetItemStockOutDetails(String itemCode) {
		itemDeliveryDAO.resetItemStockOutDetails(itemCode);
	}

	@Override
	public void resetItemStockOut(String itemCode) {
		itemDeliveryDAO.updateItemStockOutToNull(itemCode);
	}

	@Override
	public List<ItemDTO> getItemByFilterDate(String bizNumber, String option, String filterText, String startDate,
	        String endDate) {
	    if (option.equals("itemName")) {
	        return itemDeliveryDAO.getItemByItemNameDate(bizNumber, filterText, startDate, endDate);
	    } else if (option.equals("stockPlace")) {
	        return itemDeliveryDAO.getItemByStockPlaceDate(bizNumber, filterText, startDate, endDate);
	    } else if (option.equals("iDirector")) {
	        return itemDeliveryDAO.getItemByiDirectorDate(bizNumber, filterText, startDate, endDate);
	    }

	    return null;
	}

	@Override
	public List<ItemDTO> getItemsByFilter(String bizNumber, String option, String filterText) {
	    if (option.equals("itemName")) {
	        return itemDeliveryDAO.getOutItemByItemName(bizNumber, filterText);
	    } else if (option.equals("stockPlace")) {
	        return itemDeliveryDAO.getOutItemByStockOutPlace(bizNumber, filterText);
	    } else if (option.equals("ODirector")) {
	        return itemDeliveryDAO.getOutItemByODirector(bizNumber, filterText);
	    }
	    return null;
	}

	@Override
	public List<ItemDTO> getItemOutByFilterDate(String bizNumber, String option, String filterText, String startDate,
	        String endDate) {
	    if (option.equals("itemName")) {
	        return itemDeliveryDAO.getItemOutByItemNameDate(bizNumber, filterText, startDate, endDate);
	    } else if (option.equals("stockPlace")) {
	        return itemDeliveryDAO.getItemOutByStockOutPlaceDate(bizNumber, filterText, startDate, endDate);
	    } else if (option.equals("ODirector")) {
	        return itemDeliveryDAO.getItemOutByODirectorDate(bizNumber, filterText, startDate, endDate);
	    }

	    return null;
	}

	@Override
	public List<ItemDTO> getItemOutByFilter(String bizNumber, String option, String filterText) {
	    if (option.equals("itemName")) {
	        return itemDeliveryDAO.getOutItemByItemName(bizNumber, filterText);
	    } else if (option.equals("stockPlace")) {
	        return itemDeliveryDAO.getOutItemByStockOutPlace(bizNumber, filterText);
	    } else if (option.equals("ODirector")) {
	        return itemDeliveryDAO.getOutItemByODirector(bizNumber, filterText);
	    }
	    return null;
	}




}
