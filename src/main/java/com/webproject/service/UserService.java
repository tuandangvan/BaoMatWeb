package com.webproject.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.webproject.entity.User;

public interface UserService {
	
	List<User>findAll();
	
	long Count();
	
	List<User>newUsers();
	
	int countByDate(Date date);

	<S extends User> S save(S entity);

	User findByEmail(String email);
	
	Page<User>uPage(int pagenumber, int page);
}
