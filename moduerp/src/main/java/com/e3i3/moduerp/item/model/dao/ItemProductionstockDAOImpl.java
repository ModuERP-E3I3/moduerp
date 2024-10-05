package com.e3i3.moduerp.item.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

@Repository
public class ItemProductionstockDAOImpl implements ItemProductionstockDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "ItemMapper";

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
		// �����ͺ��̽� ������Ʈ ���� ����
		sqlSession.update(namespace + ".updateItem", itemDTO);
	}

	@Override
	public void deleteItemByCode(String itemCode) {
		sqlSession.delete(namespace + ".deleteItemByCode", itemCode);
	}

	// ---------------------------------------------------
	// productionOUT
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
			double outPrice, int updatedStock) {
		Map<String, Object> params = new HashMap<>();
		params.put("itemCode", itemCode);
		params.put("createdOutAt", createdOutAt);
		params.put("stockOutPlace", stockOutPlace);
		params.put("stockOut", stockOut);
		params.put("outPrice", outPrice);
		params.put("updatedStock", updatedStock);

		sqlSession.update(namespace + ".updateItemForProductionOut", params);
	}

	@Override
	public void updateStockOutByItemCode(String itemCode, int totalStockOut) {
		Map<String, Object> params = new HashMap<>();
		params.put("itemCode", itemCode);
		params.put("totalStockOut", totalStockOut);
		sqlSession.update(namespace + ".updateStockOut", params);
	}

	@Override
	public int getStockInByItemCode(String itemCode) {
		return sqlSession.selectOne(namespace + ".getStockInByItemCode", itemCode);
	}

	@Override
	public void updateStockByItemCode(String itemCode, int updatedStock) {
		Map<String, Object> params = new HashMap<>();
		params.put("itemCode", itemCode);
		params.put("updatedStock", updatedStock);
		sqlSession.update(namespace + ".updateStock", params);
	}
}
