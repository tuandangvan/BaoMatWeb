package com.webproject.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import java.util.List;
import com.webproject.entity.Order;

public interface OrderService {

	<S extends Order> S save(S entity);
	
	Page<Order>findAll(int index,int pagenumber);
	
	Page<Order>findByStatus(String status,int index,int pagenumber);
	
	Optional<Order> findById(Long id);

	long count();

	List<Order> findAllByStoreId(Long id);
	
	List<Order>findAll();
	
	List<Order>findAllByDate(String date);
	
	List<Order> findAllSortDate();

	List<Order> findAllByUserId(Long userId);

	List<Order> findAllByStoreIdAndStatus(Long id, String status);
}
