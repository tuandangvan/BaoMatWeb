package com.webproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webproject.entity.Category;
import com.webproject.service.CategoryService;

@SpringBootTest
class SpringBootWebApplicationTests {
	@Autowired
	CategoryService categoryService;
	
	@Test
	void contextLoads() {
		//tạo category
		Category category =new Category();
		category.setImage("test");
		category.setIsDeleted(false);
		category.setName("Danh mục 2");
		category.setSlug("danhmuc2");
		categoryService.save(category);
		
		//tạo 
	}

}
