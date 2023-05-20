package com.webproject.service.impl;

import java.sql.Date;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.webproject.entity.Order;
import com.webproject.repository.OrderRepo;
import com.webproject.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public <S extends Order> S save(S entity) {
		return orderRepo.save(entity);
	}

	@Override
	public Page<Order> findAll(int index, int pagenumber) {
		return orderRepo.findAll(PageRequest.of(index, pagenumber));
	}

	@Override
	public Page<Order> findByStatus(String status, int index, int pagenumber) {
		return orderRepo.findByStatus(status,PageRequest.of(index, pagenumber));
	}
	public List<Order> findAllByStoreId(Long id) {
		return orderRepo.findAllByStoreId(id);
	}

	@Override
	public long count() {
		return orderRepo.count();
	}
	@Override
	public Optional<Order> findById(Long id) {
		return orderRepo.findById(id);
	}

	@Override
	public List<Order> findAll() {
		return orderRepo.findAll();
	}

	@Override
	public List<Order> findAllByDate(String date) {
		return orderRepo.findAllByDate(date);
	}

	@Override
	public List<Order> findAllSortDate() {
		// TODO Auto-generated method stub
		return orderRepo.findAllSortDate();
	}

	@Override
	public List<Order> findAllByUserId(Long userId) {
		return orderRepo.findAllByUserId(userId);
	}

	@Override
	public List<Order> findAllByStoreIdAndStatus(Long id, String status) {
		return orderRepo.findAllByStoreIdAndStatus(id, status);
	}
	
	
	
}
