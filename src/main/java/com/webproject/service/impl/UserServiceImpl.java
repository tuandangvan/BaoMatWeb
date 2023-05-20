package com.webproject.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.webproject.entity.User;
import com.webproject.repository.UserRepo;
import com.webproject.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public long Count() {
		// TODO Auto-generated method stub
		return userRepo.count();
	}

	@Override
	public List<User> newUsers() {
		// TODO Auto-generated method stub
		return userRepo.users();
	}

	@Override
	public int countByDate(Date date) {
		// TODO Auto-generated method stub
		return userRepo.countByDate(date);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public <S extends User> S save(S entity) {
		return userRepo.save(entity);
	}

	@Override
	public Page<User> uPage(int pagenumber, int page) {
		// TODO Auto-generated method stub
		return userRepo.findAll(PageRequest.of(pagenumber, page));
	}

	
	
}
