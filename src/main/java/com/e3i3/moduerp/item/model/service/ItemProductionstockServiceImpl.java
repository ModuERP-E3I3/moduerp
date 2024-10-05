package com.e3i3.moduerp.item.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.item.model.dao.ItemProductionstockDAO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

@Service
public class ItemProductionstockServiceImpl implements ItemProductionstockService {

	@Autowired
	private ItemProductionstockDAO itemProductionstockDAO;

	@Override
	public List<String> getItemNamesByBizNumber(String bizNumber) {
		return itemProductionstockDAO.selectItemNamesByBizNumber(bizNumber);
	}

	@Override
	public List<String> getStockPlacesByBizNumber(String bizNumber) { // �߰��� �޼���
		return itemProductionstockDAO.selectStockPlacesByBizNumber(bizNumber);
	}

	@Override
	public void insertItem(ItemDTO itemDTO) {
		itemProductionstockDAO.insertItem(itemDTO);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumber(String bizNumber) {
		return itemProductionstockDAO.getItemsByBizNumber(bizNumber);
	}

	@Override
	public ItemDTO getItemDetails(String itemCode) {
		return itemProductionstockDAO.selectItemByCode(itemCode);
	}

	@Override
	public void updateItem(ItemDTO itemDTO) {
		itemProductionstockDAO.updateItem(itemDTO);
	}

	@Override
	public void deleteItemByCode(String itemCode) {
		itemProductionstockDAO.deleteItemByCode(itemCode);
	}

	// ---------------------------------------------------
	// productionOUT
	@Override
	public List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber) {
		return itemProductionstockDAO.getItemsByBizNumberOutDate(bizNumber);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumberStartingWith(String bizNumber) {
		return itemProductionstockDAO.selectItemsByBizNumberStartingWith(bizNumber);
	}

	@Override
	public void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut,
			double outPrice) {
		// 기존 재고 가져오기
		Integer currentStock = itemProductionstockDAO.getStockByItemCode(itemCode);

		// 새로운 재고 계산
		int updatedStock = currentStock - stockOut;
		// 로그 추가: 업데이트할 재고 출력
		System.out.println("Updating item stock: " + updatedStock); // 이 부분 추가
		// DB 업데이트
		itemProductionstockDAO.updateItemStockOut(itemCode, createdOutAt, stockOutPlace, stockOut, outPrice,
				updatedStock);
	}

	@Override
	public void updateItemStockOutTotal(String itemCode, int totalStockOut) {
		itemProductionstockDAO.updateStockOutByItemCode(itemCode, totalStockOut);
	}

	@Override
	public int getStockInByItemCode(String itemCode) {
		return itemProductionstockDAO.getStockInByItemCode(itemCode);
	}

	@Override
	public void updateItemStock(String itemCode, int updatedStock) {
		itemProductionstockDAO.updateStockByItemCode(itemCode, updatedStock);
	}
}