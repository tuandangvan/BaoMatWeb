package com.webproject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.entity.Category;
import com.webproject.entity.Style;

@Repository
public interface StyleRepo extends JpaRepository<Style, Long> {

	List<Style> findByCategoryId(Category categoryId);

	@Query(value = "SELECT * FROM style where categoryId=?", nativeQuery = true)
	Page<Style> filterByCategoryId(Long cateid, PageRequest of);
}
