package com.webproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.webproject.entity.Category;
import com.webproject.entity.Style;
import com.webproject.repository.StyleRepo;
import com.webproject.service.StyleService;

@Service
public class StyleServiceImpl implements StyleService {
	@Autowired
	private StyleRepo styleRepo;

	@Override
	public List<Style> findByCategoryId(Category categoryId) {
		return styleRepo.findByCategoryId(categoryId);
	}

	@Override
	public <S extends Style> S save(S entity) {
		return styleRepo.save(entity);
	}

	@Override
	public List<Style> findAll() {
		return styleRepo.findAll();
	}

	@Override
	public Page<Style> findAll(Pageable pageable) {
		return styleRepo.findAll(pageable);
	}

	@Override
	public List<Style> findAll(Sort sort) {
		return styleRepo.findAll(sort);
	}

	@Override
	public List<Style> findAllById(Iterable<Long> ids) {
		return styleRepo.findAllById(ids);
	}

	@Override
	public Optional<Style> findById(Long id) {
		return styleRepo.findById(id);
	}

	@Override
	public long count() {
		return styleRepo.count();
	}

	@Override
	public void delete(Style entity) {
		styleRepo.delete(entity);
	}

	@Override
	public Page<Style> page(int index, int size) {
		// TODO Auto-generated method stub
		return styleRepo.findAll(PageRequest.of(index, size));
	}

	@Override
	public Page<Style> pageFiterbyCate(Long cateid, int index, int size) {
		// TODO Auto-generated method stub
		return styleRepo.filterByCategoryId(cateid,PageRequest.of(index, size));
	}
	
	
	
}
