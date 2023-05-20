package com.webproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.webproject.entity.Style;
import com.webproject.entity.StyleValue;

public interface StyleValueService {

	List<StyleValue> findByStyleId(Style styleId);

	<S extends StyleValue> S save(S entity);

	List<StyleValue> findAll();

	Page<StyleValue> findAll(Pageable pageable);

	List<StyleValue> findAll(Sort sort);

	List<StyleValue> findAllById(Iterable<Long> ids);

	Optional<StyleValue> findById(Long id);

	long count();

	void deleteById(Long id);

	void delete(StyleValue entity);

}