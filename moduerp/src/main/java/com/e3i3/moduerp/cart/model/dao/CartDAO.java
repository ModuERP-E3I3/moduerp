package com.e3i3.moduerp.cart.model.dao;

import java.util.List;

import com.e3i3.moduerp.cart.model.dto.CartDTO;

public interface CartDAO {
	boolean isCartExistByBizNumber(String bizNumber);

	void insertCart(CartDTO cartDTO);

	void updateCart(CartDTO cartDTO);

	List<CartDTO> selectCartListByBizNumber(String bizNumber);

	String getCartListByBizNumber(String bizNumber);

	void updateCartList(String bizNumber, String updatedCartList);

	void clearCartList(String bizNumber);
}
