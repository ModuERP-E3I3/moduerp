package com.e3i3.moduerp.cart.model.service;

import java.util.List;

import com.e3i3.moduerp.cart.model.dto.CartDTO;

public interface CartService {
	boolean isCartExistByBizNumber(String bizNumber);

	void insertCart(CartDTO cartDTO);

	void updateCart(CartDTO cartDTO);

	List<CartDTO> selectCartListByBizNumber(String bizNumber);

	String getCartListByBizNumber(String bizNumber);

	void updateCartListByBizNumber(String bizNumber, String updatedCartList);

	void clearCartListByBizNumber(String bizNumber);

	void deleteCartList(String bizNumber);
}
