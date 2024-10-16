package com.e3i3.moduerp.item.model.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;
import com.e3i3.moduerp.item.model.dao.ItemSalesStockDAO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

@Service
public class ItemSalesStockServiceImpl implements ItemSalesStockService {

	@Autowired
	private ItemSalesStockDAO itemSalesStockDAO;

	@Override
	public List<String> getSalesItemNamesByBizNumber(String bizNumber) {
		return itemSalesStockDAO.selectSalesItemNamesByBizNumber(bizNumber);
	}

	@Override
	public List<String> getStockPlacesByBizNumber(String bizNumber) { // �߰��� �޼���
		return itemSalesStockDAO.selectStockPlacesByBizNumber(bizNumber);
	}

	@Override
	public void insertItem(ItemDTO itemDTO) {
		itemSalesStockDAO.insertItem(itemDTO);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumber(String bizNumber) {
		return itemSalesStockDAO.getItemsByBizNumber(bizNumber);
	}

	@Override
	public ItemDTO getItemDetails(String itemCode) {
		return itemSalesStockDAO.selectItemByCode(itemCode);
	}

	@Override
	public void updateItem(ItemDTO itemDTO) {
		itemSalesStockDAO.updateItem(itemDTO);
	}

	@Override
	public void deleteItemByCode(String itemCode) {
		itemSalesStockDAO.deleteItemByCode(itemCode);
	}

	// ---------------------------------------------------
	// salesOUT
	@Override
	public List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber) {
		return itemSalesStockDAO.getItemsByBizNumberOutDate(bizNumber);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumberStartingWith(String bizNumber) {
		return itemSalesStockDAO.selectItemsByBizNumberStartingWith(bizNumber);
	}

	@Override
	public void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut,
			double outPrice, String oDirector) {
		// 기존 재고 가져오기
		Integer currentStock = itemSalesStockDAO.getStockByItemCode(itemCode);

		// 새로운 재고 계산
		int updatedStock = currentStock - stockOut;
		// 로그 추가: 업데이트할 재고 출력
		System.out.println("Updating item stock: " + updatedStock); // 이 부분 추가
		// DB 업데이트
		itemSalesStockDAO.updateItemStockOut(itemCode, createdOutAt, stockOutPlace, stockOut, outPrice, updatedStock,
				oDirector);
	}

	@Override
	public void updateItemStockOutTotal(String itemCode, int totalStockOut) {
		itemSalesStockDAO.updateStockOutByItemCode(itemCode, totalStockOut);
	}

	@Override
	public int getStockInByItemCode(String itemCode) {
		return itemSalesStockDAO.getStockInByItemCode(itemCode);
	}

	@Override
	public void updateItemStock(String itemCode, int updatedStock) {
		itemSalesStockDAO.updateStockByItemCode(itemCode, updatedStock);
	}

	@Override
	public void updateItemCreatedOutAt(String itemCode, Timestamp createdOutAt) {
		itemSalesStockDAO.updateItemCreatedOutAt(itemCode, createdOutAt);
	}

	@Override
	public void updateItemOutPrice(String itemCode, double outPrice) {
		itemSalesStockDAO.updateItemOutPrice(itemCode, outPrice);
	}

	@Override
	public void updateItemWithLatestStockOut(String itemCode, Timestamp latestOutDate, double latestOutPrice,
			String latestOutPlace) {
		itemSalesStockDAO.updateItemWithLatestStockOut(itemCode, latestOutDate, latestOutPrice, latestOutPlace);
	}

	@Override
	public void resetItemStockOutDetails(String itemCode) {
		itemSalesStockDAO.resetItemStockOutDetails(itemCode);
	}

	@Override
	public void resetItemStockOut(String itemCode) {
		// DAO를 통해 ITEM 테이블을 업데이트하는 로직
		itemSalesStockDAO.updateItemStockOutToNull(itemCode);
	}

	// -----------------------------------------------
	// sales in filter   !!!!
	@Override
	public List<ItemDTO> getItemByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate) {
		if (option.equals("itemName")) {
			return itemSalesStockDAO.getItemByItemNameDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("stockPlace")) {
			return itemSalesStockDAO.getItemByStockPlaceDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("iDirector")) {
			return itemSalesStockDAO.getItemByiDirectorDate(bizNumber, filterText, startDate, endDate);
		}

		return null;

	}

	@Override
	public List<ItemDTO> getItemsByFilter(String bizNumber, String option, String filterText) {
		if (option.equals("itemName")) {
			return itemSalesStockDAO.getItemByItemName(bizNumber, filterText);
		} else if (option.equals("stockPlace")) {
			return itemSalesStockDAO.getItemByStockPlace(bizNumber, filterText);
		} else if (option.equals("iDirector")) {
			return itemSalesStockDAO.getItemByiDirector(bizNumber, filterText);
		}
		return null;
	}

	// -----------------------------------------------
	// sales Out filter   !!!!!
	@Override
	public List<ItemDTO> getItemOutByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate) {
		if (option.equals("itemName")) {
			return itemSalesStockDAO.getItemOutByItemNameDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("stockPlace")) {
			return itemSalesStockDAO.getItemOutByStockOutPlaceDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("ODirector")) {
			return itemSalesStockDAO.getItemOutByODirectorDate(bizNumber, filterText, startDate, endDate);
		}

		return null;
	}

	@Override
	public List<ItemDTO> getItemOutByFilter(String bizNumber, String option, String filterText) {
		if (option.equals("itemName")) {
			return itemSalesStockDAO.getOutItemByItemName(bizNumber, filterText);
		} else if (option.equals("stockPlace")) {
			return itemSalesStockDAO.getOutItemByStockOutPlace(bizNumber, filterText);
		} else if (option.equals("ODirector")) {
			return itemSalesStockDAO.getOutItemByODirector(bizNumber, filterText);
		}
		return null;
	}
	
	// filter
	@Override
	public List<ItemDTO> getItemOutByFilterOnlyDate(String bizNumber, String startDate, String endDate) {
		return itemSalesStockDAO.getItemOutByFilterOnlyDate(bizNumber, startDate, endDate);
	}

	@Override
	public List<ItemDTO> getItemOutByFilterStartDate(String bizNumber, String startDate) {
		return itemSalesStockDAO.getItemOutByFilterStartDate(bizNumber, startDate);
	}

	@Override
	public List<ItemDTO> getItemOutByFilterEndDate(String bizNumber, String endDate) {
		return itemSalesStockDAO.getItemOutByFilterEndDate(bizNumber, endDate);
	}

	@Override
	public List<ItemDTO> getItemByFilterOnlyDate(String bizNumber, String startDate, String endDate) {
		return itemSalesStockDAO.getItemByFilterOnlyDate(bizNumber, startDate, endDate);
	}

	@Override
	public List<ItemDTO> getItemByFilterStartDate(String bizNumber, String startDate) {
		return itemSalesStockDAO.getItemByFilterStartDate(bizNumber, startDate);
	}

	@Override
	public List<ItemDTO> getItemByFilterEndDate(String bizNumber, String endDate) {
		return itemSalesStockDAO.getItemByFilterEndDate(bizNumber, endDate);
	}
	
	
}
