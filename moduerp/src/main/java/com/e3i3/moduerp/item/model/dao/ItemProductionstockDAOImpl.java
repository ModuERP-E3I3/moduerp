package com.e3i3.moduerp.item.model.dao;

import java.util.List;

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
}
