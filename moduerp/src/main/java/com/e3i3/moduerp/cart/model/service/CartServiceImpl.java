package com.e3i3.moduerp.cart.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.cart.model.dao.CartDAO;
import com.e3i3.moduerp.cart.model.dto.CartDTO;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;

	@Override
	public boolean isCartExistByBizNumber(String bizNumber) {
		return cartDAO.isCartExistByBizNumber(bizNumber);
	}

	@Override
	public void insertCart(CartDTO cartDTO) {
		cartDAO.insertCart(cartDTO);

	}

	@Override
	public void updateCart(CartDTO cartDTO) {
		cartDAO.updateCart(cartDTO);

	}

	@Override
	public List<CartDTO> selectCartListByBizNumber(String bizNumber) {
		return cartDAO.selectCartListByBizNumber(bizNumber);
	}

	@Override
	public String getCartListByBizNumber(String bizNumber) {
		return cartDAO.getCartListByBizNumber(bizNumber);
	}

	@Override
	public void updateCartListByBizNumber(String bizNumber, String updatedCartList) {
		cartDAO.updateCartList(bizNumber, updatedCartList);
	}

	@Override
	public void clearCartListByBizNumber(String bizNumber) {
		cartDAO.clearCartList(bizNumber);
	}

	@Override
	public void deleteCartList(String bizNumber) {
		cartDAO.deleteCartList(bizNumber);
		
	}
}
