package com.webproject.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webproject.service.UserService;
import com.webproject.entity.*;
@Controller
@RequestMapping("admin/user")
public class AdminUserController {
	@Autowired
	UserService userService;
	
	@GetMapping("{index}")
	public String User(ModelMap modelMap,@PathVariable("index") String index){
		if(index==null) {
			index="1";
		}
		int indexp = Integer.parseInt(index);
		Page<User>usPage=userService.uPage(indexp-1, 6);
		List<User>users=usPage.getContent();
		modelMap.addAttribute("listuser", users);
		modelMap.addAttribute("page", usPage);
		return "admin/Table/user";
	}
	
	@GetMapping("chart")
	public String chart(ModelMap modelMap) {
		List<com.webproject.entity.User>users=userService.newUsers();
		modelMap.addAttribute("listuser", users);
		return "admin/chart/user";
	}
}
