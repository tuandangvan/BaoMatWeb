package com.webproject.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webproject.entity.OrderItem;
import com.webproject.repository.OrderItemRepo;
import com.webproject.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private OrderItemRepo orderItemRepo;

	@Override
	public <S extends OrderItem> S save(S entity) {
		return orderItemRepo.save(entity);
	}

	@Override
	public long count() {
		return orderItemRepo.count();
	}

	@Override
	public List<OrderItem> findByOrderId(Long id) {
		return orderItemRepo.findByOrderId(id);
	}

	
}
