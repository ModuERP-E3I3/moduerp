package com.e3i3.moduerp.item.model.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

@Repository
public class ItemDeliveryDAOImpl implements ItemDeliveryDAO{

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "ItemDeliveryMapper";

	@Override
	public List<String> selectItemNamesByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".selectItemNamesByBizNumber", bizNumber);
	}

	@Override
	public List<String> selectStockPlacesByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".selectStockPlacesByBizNumber", bizNumber);
	}

	@Override
	public void insertItem(ItemDTO itemDTO) {
		sqlSession.insert(namespace + ".insertItem", itemDTO);
	}

	@Override
	public List<ItemDTO> getItemsByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".getItemsByBizNumber", bizNumber);
	}

	@Override
	public ItemDTO selectItemByCode(String itemCode) {
		return sqlSession.selectOne(namespace + ".selectItemByCode", itemCode);
	}

	@Override
	public void updateItem(ItemDTO itemDTO) {

		sqlSession.update(namespace + ".updateItem", itemDTO);
	}

	@Override
	public void deleteItemByCode(String itemCode) {
		sqlSession.delete(namespace + ".deleteItemByCode", itemCode);
	}


	@Override
	public List<ItemDTO> getItemsByBizNumberOutDate(String bizNumber) {
		return sqlSession.selectList(namespace + ".getItemsByBizNumberOutDate", bizNumber);
	}

	@Override
	public List<ItemDTO> selectItemsByBizNumberStartingWith(String bizNumber) {
		return sqlSession.selectList(namespace + ".selectItemsByBizNumberStartingWith", bizNumber);
	}

	@Override
	public int getStockByItemCode(String itemCode) {
		return sqlSession.selectOne(namespace + ".getStockByItemCode", itemCode);
	}

	@Override
	public void updateItemStockOut(String itemCode, String createdOutAt, String stockOutPlace, int stockOut,
			double outPrice, int updatedStock, String oDirector) {
		Map<String, Object> barams = new HashMap<>();
		barams.put("itemCode", itemCode);
		barams.put("createdOutAt", createdOutAt);
		barams.put("stockOutPlace", stockOutPlace);
		barams.put("stockOut", stockOut);
		barams.put("outPrice", outPrice);
		barams.put("updatedStock", updatedStock);
		barams.put("oDirector", oDirector);

		sqlSession.update(namespace + ".updateItemForBuyOut", barams);
	}

	@Override
	public void updateStockOutByItemCode(String itemCode, int totalStockOut) {
		Map<String, Object> barams = new HashMap<>();
		barams.put("itemCode", itemCode);
		barams.put("totalStockOut", totalStockOut);
		sqlSession.update(namespace + ".updateStockOut", barams);
	}


	@Override
	public void updateStockByItemCode(String itemCode, int updatedStock) {
		Map<String, Object> barams = new HashMap<>();
		barams.put("itemCode", itemCode);
		barams.put("updatedStock", updatedStock);
		sqlSession.update(namespace + ".updateStock", barams);
	}
	
	@Override
	public int getStockInByItemCode(String itemCode) {
		return sqlSession.selectOne(namespace + ".getStockInByItemCode", itemCode);
	}

	@Override
	public void updateItemCreatedOutAt(String itemCode, Timestamp createdOutAt) {
		sqlSession.update(namespace + ".updateItemCreatedOutAt",
				Map.of("itemCode", itemCode, "createdOutAt", createdOutAt));
	}

	@Override
	public void updateItemOutPrice(String itemCode, double outPrice) {
		sqlSession.update(namespace + ".updateItemOutPrice", Map.of("itemCode", itemCode, "outPrice", outPrice));
	}

	@Override
	public void updateItemWithLatestStockOut(String itemCode, Timestamp latestOutDate, double latestOutPrice,
			String latestOutPlace) {
		sqlSession.update(namespace + ".updateItemWithLatestStockOut", Map.of("itemCode", itemCode, "latestOutDate",
				latestOutDate, "latestOutPrice", latestOutPrice, "latestOutPlace", latestOutPlace));
	}

	@Override
	public void resetItemStockOutDetails(String itemCode) {
		sqlSession.update(namespace + ".resetItemStockOutDetails", itemCode);
	}

	@Override
	public void updateItemStockOutToNull(String itemCode) {
		sqlSession.update(namespace + ".updateItemStockOutToNull", itemCode);
	}

	
	// in filter
	@Override
	public List<ItemDTO> getItemByItemNameDate(String bizNumber, String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectItemFilterByItemNameDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));

	}

	@Override
	public List<ItemDTO> getItemByStockPlaceDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectItemFilterByStockPlaceDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<ItemDTO> getItemByiDirectorDate(String bizNumber, String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectItemFilterByiDirectorDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<ItemDTO> getItemByItemName(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectItemFilterByItemName",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<ItemDTO> getItemByStockPlace(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectItemFilterByStockPlace",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<ItemDTO> getItemByiDirector(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectItemFilterByiDirector",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	// -----------------------------------------------
	// Out filter

	@Override
	public List<ItemDTO> getItemOutByItemNameDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		System.out.println("DAO 실행");
		return sqlSession.selectList(namespace + ".selectItemOutFilterByItemNameDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));

	}

	@Override
	public List<ItemDTO> getItemOutByStockOutPlaceDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectItemOutFilterByStockOutPlaceDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));

	}

	@Override
	public List<ItemDTO> getItemOutByODirectorDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectItemOutFilterByODirectorDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));

	}

	@Override
	public List<ItemDTO> getOutItemByItemName(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectItemOutFilterByItemName",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<ItemDTO> getOutItemByStockOutPlace(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectItemOutFilterByStockOutPlace",
				Map.of("bizNumber", bizNumber, "filterText", filterText));

	}

	@Override
	public List<ItemDTO> getOutItemByODirector(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectItemOutFilterByODirector",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<ItemDTO> getItemByFilterOnlyDate(String bizNumber, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectItemByFilterOnlyDate",
				Map.of("bizNumber", bizNumber, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<ItemDTO> getItemByFilterStartDate(String bizNumber, String startDate) {
		return sqlSession.selectList(namespace + ".selectItemByFilterStartDate",
				Map.of("bizNumber", bizNumber, "startDate", startDate));
	}

	@Override
	public List<ItemDTO> getItemByFilterEndDate(String bizNumber, String endDate) {
		return sqlSession.selectList(namespace + ".selectItemByFilterEndDate",
				Map.of("bizNumber", bizNumber, "endDate", endDate));
	}


	



}
