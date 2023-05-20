package com.webproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.webproject.entity.Category;

public interface CategoryService {

	Optional<Category> findById(Long id);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);
	
	void deleteById(Long id);

	Category findBySlug(String slug);

	List<Category> searchCategoryByName(String keyword);

}