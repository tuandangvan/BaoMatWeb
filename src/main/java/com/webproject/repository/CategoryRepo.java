package com.webproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
	
	Category findBySlug(String slug);
	
	@Query(value = "Select * from Category where name LIKE ?", nativeQuery = true)
	List<Category> searchCategoryByName(String keyword);
}
