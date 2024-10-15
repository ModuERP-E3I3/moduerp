package com.e3i3.moduerp.item.model.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.item.model.dao.ItemBuyStockDAO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

@Service
public class ItemBuyStockServiceImpl implements ItemBuyStockService {

	@Autowired
	private ItemBuyStockDAO itemBuyStockDAO;

	@Override
	public List<String> getItemNamesByBizNumber(String bizNumber) {
		return itemBuyStockDAO.selectItemNamesByBizNumber(bizNumber);
	}

	@Override
	public List<String> getStockPlacesByBizNumber(String bizNumber) { // �߰��� �޼���
		return itemBuyStockDAO.selectStockPlacesByBizNumber(bizNumber);
	}

	@Override
	public void insertItem(ItemDTO itemDTO) {
		itemBuyStockDAO.insertItem(itemDTO);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumber(String bizNumber) {
		return itemBuyStockDAO.getItemsByBizNumber(bizNumber);
	}

	@Override
	public ItemDTO getItemDetails(String itemCode) {
		return itemBuyStockDAO.selectItemByCode(itemCode);
	}

	@Override
	public void updateItem(ItemDTO itemDTO) {
		itemBuyStockDAO.updateItem(itemDTO);
	}

	@Override
	public void deleteItemByCode(String itemCode) {
		itemBuyStockDAO.deleteItemByCode(itemCode);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber) {
		return itemBuyStockDAO.getItemsByBizNumberOutDate(bizNumber);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumberStartingWith(String bizNumber) {
		return itemBuyStockDAO.selectItemsByBizNumberStartingWith(bizNumber);
	}

	@Override
	public void updateItemStockOut(String itemCode, String createdOutAt, String StockOutPlace, int StockOut,
			double outPrice, String oDirector) {

		Integer currentStock = itemBuyStockDAO.getStockByItemCode(itemCode);

		int updatedStock = currentStock - StockOut;

		System.out.println("Updating item Stock: " + updatedStock);
		itemBuyStockDAO.updateItemStockOut(itemCode, createdOutAt, StockOutPlace, StockOut, outPrice,
				updatedStock, oDirector);
	}

	@Override
	public void updateItemStockOutTotal(String itemCode, int totalStockOut) {
		itemBuyStockDAO.updateStockOutByItemCode(itemCode, totalStockOut);
	}

	@Override
	public int getStockInByItemCode(String itemCode) {
		return itemBuyStockDAO.getStockInByItemCode(itemCode);
	}

	@Override
	public void updateItemStock(String itemCode, int updatedStock) {
		itemBuyStockDAO.updateStockByItemCode(itemCode, updatedStock);
	}

	@Override
	public void updateItemCreatedOutAt(String itemCode, Timestamp createdOutAt) {
		itemBuyStockDAO.updateItemCreatedOutAt(itemCode, createdOutAt);
	}

	@Override
	public void updateItemOutPrice(String itemCode, double outPrice) {
		itemBuyStockDAO.updateItemOutPrice(itemCode, outPrice);
	}

	@Override
	public void updateItemWithLatestStockOut(String itemCode, Timestamp latestOutDate, double latestOutPrice,
			String latestOutPlace) {
		itemBuyStockDAO.updateItemWithLatestStockOut(itemCode, latestOutDate, latestOutPrice, latestOutPlace);
	}

	@Override
	public void resetItemStockOutDetails(String itemCode) {
		itemBuyStockDAO.resetItemStockOutDetails(itemCode);
	}

	@Override
	public void resetItemStockOut(String itemCode) {
		itemBuyStockDAO.updateItemStockOutToNull(itemCode);
	}

	@Override
	public List<ItemDTO> getItemByFilterDate(String bizNumber, String option, String filterText, String startDate,
	        String endDate) {
	    if (option.equals("itemName")) {
	        return itemBuyStockDAO.getItemByItemNameDate(bizNumber, filterText, startDate, endDate);
	    } else if (option.equals("stockPlace")) {
	        return itemBuyStockDAO.getItemByStockPlaceDate(bizNumber, filterText, startDate, endDate);
	    } else if (option.equals("iDirector")) {
	        return itemBuyStockDAO.getItemByiDirectorDate(bizNumber, filterText, startDate, endDate);
	    }

	    return null;
	}

	@Override
	public List<ItemDTO> getItemsByFilter(String bizNumber, String option, String filterText) {
	    if (option.equals("itemName")) {
	        return itemBuyStockDAO.getItemByItemName(bizNumber, filterText);
	    } else if (option.equals("stockPlace")) {
	        return itemBuyStockDAO.getItemByStockPlace(bizNumber, filterText);
	    } else if (option.equals("iDirector")) {
	        return itemBuyStockDAO.getItemByiDirector(bizNumber, filterText);
	    }
	    return null;
	}

	@Override
	public List<ItemDTO> getItemOutByFilterDate(String bizNumber, String option, String filterText, String startDate,
	        String endDate) {
	    if (option.equals("itemName")) {
	        return itemBuyStockDAO.getItemOutByItemNameDate(bizNumber, filterText, startDate, endDate);
	    } else if (option.equals("stockPlace")) {
	        return itemBuyStockDAO.getItemOutByStockOutPlaceDate(bizNumber, filterText, startDate, endDate);
	    } else if (option.equals("ODirector")) {
	        return itemBuyStockDAO.getItemOutByODirectorDate(bizNumber, filterText, startDate, endDate);
	    }

	    return null;
	}

	@Override
	public List<ItemDTO> getItemOutByFilter(String bizNumber, String option, String filterText) {
	    if (option.equals("itemName")) {
	        return itemBuyStockDAO.getOutItemByItemName(bizNumber, filterText);
	    } else if (option.equals("stockPlace")) {
	        return itemBuyStockDAO.getOutItemByStockOutPlace(bizNumber, filterText);
	    } else if (option.equals("ODirector")) {
	        return itemBuyStockDAO.getOutItemByODirector(bizNumber, filterText);
	    }
	    return null;
	}




}
