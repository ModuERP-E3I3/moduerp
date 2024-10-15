package com.e3i3.moduerp.delivery.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.buystock.model.dto.BuyStockInDTO;
import com.e3i3.moduerp.delivery.model.dto.DeliveryDTO;

@Repository
public class DeliveryDAOImpl implements DeliveryDAO{

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "DeliveryMapper";

	@Override
	public List<DeliveryDTO> getAllDelivery() {
		return sqlSession.selectList(namespace + ".getAllDelivery");
	}

	@Override
	public void insertDelivery(DeliveryDTO deliveryDTO) {
		sqlSession.insert(namespace + ".insertDelivery", deliveryDTO);
		
	}

	@Override
	public DeliveryDTO selectDeliveryByItemCode(String itemCode) {
		return sqlSession.selectOne(namespace + ".selectDeliveryByItemCode", itemCode);
	}

	@Override
	public void updateDelivery(DeliveryDTO deliveryDTO) {
		sqlSession.update(namespace + ".updateDelivery", deliveryDTO);
		
	}

	@Override
	public void deleteDeliveryByItemCode(String itemCode) {
		sqlSession.delete(namespace + ".deleteDeliveryByItemCode", itemCode);
		
	}

	@Override
	public List<DeliveryDTO> getAllDeliveryTableList() {
		return null;
	}

	@Override
	public List<String> getItemItemCode(String bizNumber) {
		return sqlSession.selectList(namespace + ".getItemItemCode", bizNumber);
		
	}

}
