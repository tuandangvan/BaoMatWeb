package com.webproject.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	@Query(value = "SELECT top(100)* FROM USERS order by createdAt desc", nativeQuery = true)
	List<User> users();
	
	@Query(value = "SELECT count(*) FROM USERS where createdAt=?", nativeQuery = true)
	int countByDate(Date date);
	
	@Query(value = "SELECT count(*) FROM USERS where year(createdAt)=?", nativeQuery = true)
	int countByYear(int year);
	
	@Query(value = "SELECT count(*) FROM USERS where month(createdAt)=?", nativeQuery = true)
	int countByMonth(int month);
	
	User findByEmail(String email);
	
//	@Query(value="SELECT u FROM USERS u")
//	Page<User> listAllPage(Pageable pageable);
	
}
