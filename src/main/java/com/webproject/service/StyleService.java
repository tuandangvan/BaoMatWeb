package com.webproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.webproject.entity.Category;
import com.webproject.entity.Style;

public interface StyleService {

	List<Style> findByCategoryId(Category categoryId);

	<S extends Style> S save(S entity);

	List<Style> findAll();

	Page<Style> findAll(Pageable pageable);

	List<Style> findAll(Sort sort);

	List<Style> findAllById(Iterable<Long> ids);

	Optional<Style> findById(Long id);

	long count();

	void delete(Style entity);

	Page<Style> page(int index, int size);
	
	Page<Style> pageFiterbyCate(Long cateid, int index, int size);
}