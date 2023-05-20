package com.webproject.service;

import java.util.List;
import java.util.Optional;

import com.webproject.entity.Cart;

public interface CartService {

	<S extends Cart> S save(S entity);
	
	Optional<Cart> findCartByUserIdAndStoreId(Long userId, Long storeId);

	List<Long> getAllCartIdOfUser(Long userId);

	void delete(Cart entity);

	Optional<Cart> findById(Long id);

}
