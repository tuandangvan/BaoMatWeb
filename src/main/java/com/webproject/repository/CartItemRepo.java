package com.webproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.entity.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
	@Query(value = "Select * from CartItem where cartId=?1 and productId = ?2", nativeQuery = true)
	Optional<CartItem> findCartItemByCartIdAndProductId(Long cartId, Long productId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "Update CartItem set count = ?1, cartId=?2, productId=?3", nativeQuery = true)
	void updateCartItem(int count, Long cartId, Long productId) throws Exception;
	
	@Query(value = "SELECT * FROM CartItem where cartId = ?", nativeQuery = true)
	List<CartItem> findCartItemByCartId(Long cartId);
}
