package com.webproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webproject.entity.Style;
import com.webproject.entity.StyleValue;

@Repository
public interface StyleValueRepo extends JpaRepository<StyleValue, Long> {
	List<StyleValue> findByStyleId(Style styleId);
}
