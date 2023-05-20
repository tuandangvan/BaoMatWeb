package com.webproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.entity.Category;
import com.webproject.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	
	@Query(value = "Select * from Product where storeId = ?", nativeQuery = true)
	List<Product> findAllByStoreId(Long storeId);
	
	@Query(value = "SELECT TOP 25 * FROM Product ORDER BY _id DESC", nativeQuery = true)
	List<Product> findLastestProduct();
	
	@Query(value = "Select * from Product where categoryId = ?", nativeQuery = true)
	List<Product> findAllByCategoryId(Long categoryId);
	
	@Query(value = "Select * from Product where name LIKE ?", nativeQuery = true)
	List<Product> searchProductByName(String keyword);
}
