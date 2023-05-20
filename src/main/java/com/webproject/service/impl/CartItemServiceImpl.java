package com.webproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webproject.entity.Cart;
import com.webproject.entity.CartItem;
import com.webproject.repository.CartItemRepo;
import com.webproject.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartItemRepo cartItemRepo;

	@Override
	public Optional<CartItem> findCartItemByCartIdAndProductId(Long cartId, Long productId) {
		return cartItemRepo.findCartItemByCartIdAndProductId(cartId, productId);
	}

	@Override
	public <S extends CartItem> S save(S entity) {
		return cartItemRepo.save(entity);
	}

	@Override
	public void updateCartItem(int count, Long cartId, Long productId) throws Exception {
		cartItemRepo.updateCartItem(count, cartId, productId);
	}

	@Override
	public List<CartItem> findCartItemByCartId(Long cartId) {
		return cartItemRepo.findCartItemByCartId(cartId);
	}

	@Override
	public Optional<CartItem> findById(Long id) {
		return cartItemRepo.findById(id);
	}

	@Override
	public void delete(CartItem entity) {
		cartItemRepo.delete(entity);
	}
	
	
	
}
