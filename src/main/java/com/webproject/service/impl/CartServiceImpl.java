package com.webproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.webproject.entity.Cart;
import com.webproject.repository.CartRepo;
import com.webproject.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepo cartRepo;

	@Override
	public Optional<Cart> findCartByUserIdAndStoreId(Long userId, Long storeId) {
		return cartRepo.findCartByUserIdAndStoreId(userId, storeId);
	}

	@Override
	public <S extends Cart> S save(S entity) {
		return cartRepo.save(entity);
	}

	@Override
	public List<Long> getAllCartIdOfUser(Long userId) {
		return cartRepo.getAllCartIdOfUser(userId);
	}

	@Override
	public void delete(Cart entity) {
		cartRepo.delete(entity);
	}

	@Override
	public Optional<Cart> findById(Long id) {
		return cartRepo.findById(id);
	}
	
	
	
}
