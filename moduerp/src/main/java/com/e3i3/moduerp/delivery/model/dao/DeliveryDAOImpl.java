package com.e3i3.moduerp.delivery.model.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.delivery.model.dto.DeliveryDTO;
import com.e3i3.moduerp.item.model.dto.ItemDTO;

public class DeliveryDAOImpl implements DeliveryDAO{

	@Override
	public List<ItemDTO> getAllItemsByBizNumber(String bizNumber) {
		return null;
				//sqlSession.selectList(namespace + ".getAllItemsByBizNumber", bizNumber);
	}



}
