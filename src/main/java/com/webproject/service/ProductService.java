package com.webproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.webproject.entity.Category;
import com.webproject.entity.Product;

public interface ProductService {

	<S extends Product> S save(S entity);

	List<Product> findAll();

	Page<Product> findAll(Pageable pageable);

	List<Product> findAll(Sort sort);

	Optional<Product> findById(Long id);

	void deleteById(Long id);

	List<Product> findAllByStoreId(Long storeId);

	long count();
	
	Page<Product>page(int index, int size);

	List<Product> findLastestProduct();

	List<Product> findAllByCategoryId(Long categoryId);

	List<Product> searchProductByName(String keyword);


}