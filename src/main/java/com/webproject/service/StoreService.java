package com.webproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.webproject.entity.Store;

public interface StoreService {

	<S extends Store> S save(S entity);

	List<Store> findAll();

	Page<Store> findAll(Pageable pageable);

	List<Store> findAll(Sort sort);

	Optional<Store> findById(Long id);

	long count();

	void deleteById(Long id);

	void delete(Store entity);

	void deleteAll();

	Store findByOwnerId(Long ownerId);

	void updateStore(Store entity) throws Exception;
	
	Page<Store>page(int index, int size);
	
}