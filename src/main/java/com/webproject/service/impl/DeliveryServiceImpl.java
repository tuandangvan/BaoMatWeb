package com.webproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webproject.entity.Delivery;
import com.webproject.repository.DeliveryRepo;
import com.webproject.service.DeliveryService;


@Service
public class DeliveryServiceImpl implements DeliveryService{
	
	@Autowired
	private DeliveryRepo deliveryRepo;

	@Override
	public Optional<Delivery> findById(Long id) {
		// TODO Auto-generated method stub
		return deliveryRepo.findById(id);
	}

	@Override
	public Page<Delivery> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return deliveryRepo.findAll(pageable);
	}

	@Override
	public List<Delivery> findAll() {
		// TODO Auto-generated method stub
		return deliveryRepo.findAll();
	}

	@Override
	public <S extends Delivery> S save(S entity) {
		// TODO Auto-generated method stub
		return deliveryRepo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		deliveryRepo.deleteById(id);
	}

	@Override
	public Page<Delivery> page(int index, int size) {
		// TODO Auto-generated method stub
		return deliveryRepo.findAll(PageRequest.of(index, size));
	}

}
