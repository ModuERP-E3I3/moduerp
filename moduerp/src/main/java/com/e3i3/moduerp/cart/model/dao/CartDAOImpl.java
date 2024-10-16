package com.e3i3.moduerp.cart.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.cart.model.dto.CartDTO;

@Repository
public class CartDAOImpl implements CartDAO {
	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "CartMapper";

	@Override
	public boolean isCartExistByBizNumber(String bizNumber) {
		Integer count = sqlSession.selectOne(namespace + ".isCartExistByBizNumber", bizNumber);
		return count != null && count > 0;
	}

	@Override
	public void insertCart(CartDTO cartDTO) {
		sqlSession.insert(namespace + ".insertCart", cartDTO);

	}

	@Override
	public void updateCart(CartDTO cartDTO) {
		sqlSession.update(namespace + ".updateCart", cartDTO);
	}

	@Override
	public List<CartDTO> selectCartListByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".selectCartListByBizNumber", bizNumber);
	}

	@Override
	public String getCartListByBizNumber(String bizNumber) {
		return sqlSession.selectOne(namespace + ".getCartListByBizNumber", bizNumber);
	}

	@Override
	public void updateCartList(String bizNumber, String updatedCartList) {
		// 파라미터로 전달할 데이터 설정
		Map<String, Object> params = new HashMap<>();
		params.put("bizNumber", bizNumber);
		params.put("updatedCartList", updatedCartList);

		// SQL 실행
		sqlSession.update(namespace + ".updateCartListByBizNumber", params);
	}

	@Override
	public void clearCartList(String bizNumber) {
		sqlSession.update(namespace + ".clearCartList", bizNumber);
	}

	@Override
	public void deleteCartList(String bizNumber) {
		sqlSession.update(namespace + ".updateCartItemListToNull", bizNumber);
		
	}
}
