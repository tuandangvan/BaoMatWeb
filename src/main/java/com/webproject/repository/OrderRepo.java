package com.webproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

	@Query(value = "SELECT * FROM orders where status=?", nativeQuery = true)
	Page<Order> findByStatus(String status, PageRequest of);

	@Query(value = "Select * from Orders where storeId=?1", nativeQuery = true)
	List<Order> findAllByStoreId(Long id);
	
	@Query(value = "SELECT * FROM Orders where CONVERT(date, createdAt, 103)=?", nativeQuery = true)
	List<Order> findAllByDate(String date);
	
	@Query(value = "SELECT * FROM Orders order by createdAt desc", nativeQuery = true)
	List<Order> findAllSortDate();
	
	@Query(value = "Select * from Orders where userId=?1", nativeQuery = true)
	List<Order> findAllByUserId(Long userId);
	
	@Query(value = "SELECT * FROM orders where storeId = ?1 and status = ?2", nativeQuery = true)
	List<Order> findAllByStoreIdAndStatus(Long id, String status);
}
