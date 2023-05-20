package com.webproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.webproject.entity.Style;
import com.webproject.entity.StyleValue;
import com.webproject.repository.StyleValueRepo;
import com.webproject.service.StyleValueService;

@Service
public class StyleValueServiceImpl implements StyleValueService {
	@Autowired
	private StyleValueRepo styleValueRepo;

	@Override
	public List<StyleValue> findByStyleId(Style styleId) {
		return styleValueRepo.findByStyleId(styleId);
	}

	@Override
	public <S extends StyleValue> S save(S entity) {
		return styleValueRepo.save(entity);
	}

	@Override
	public List<StyleValue> findAll() {
		return styleValueRepo.findAll();
	}

	@Override
	public Page<StyleValue> findAll(Pageable pageable) {
		return styleValueRepo.findAll(pageable);
	}

	@Override
	public List<StyleValue> findAll(Sort sort) {
		return styleValueRepo.findAll(sort);
	}

	@Override
	public List<StyleValue> findAllById(Iterable<Long> ids) {
		return styleValueRepo.findAllById(ids);
	}

	@Override
	public Optional<StyleValue> findById(Long id) {
		return styleValueRepo.findById(id);
	}

	@Override
	public long count() {
		return styleValueRepo.count();
	}

	@Override
	public void deleteById(Long id) {
		styleValueRepo.deleteById(id);
	}

	@Override
	public void delete(StyleValue entity) {
		styleValueRepo.delete(entity);
	}
	
	
}
