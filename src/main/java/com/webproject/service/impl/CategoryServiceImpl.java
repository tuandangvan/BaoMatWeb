package com.webproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.webproject.entity.Category;
import com.webproject.repository.CategoryRepo;
import com.webproject.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public <S extends Category> S save(S entity) {
		return categoryRepo.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepo.findAll(pageable);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepo.findAll(sort);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryRepo.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		categoryRepo.deleteById(id);
	}
	
	@Override
	public Category findBySlug(String slug) {
		return categoryRepo.findBySlug(slug);
	}

	@Override
	public List<Category> searchCategoryByName(String keyword) {
		return categoryRepo.searchCategoryByName(keyword);
	}
	
	
}
