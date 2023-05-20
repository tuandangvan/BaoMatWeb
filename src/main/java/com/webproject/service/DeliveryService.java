package com.webproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webproject.entity.Delivery;

public interface DeliveryService {
	Optional<Delivery> findById(Long id);

	Page<Delivery> findAll(Pageable pageable);

	List<Delivery> findAll();

	<S extends Delivery> S save(S entity);

	void deleteById(Long id);

	Page<Delivery> page(int index, int size);
}
