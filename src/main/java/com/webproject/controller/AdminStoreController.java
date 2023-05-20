package com.webproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webproject.entity.Store;
import com.webproject.service.StoreService;

@Controller
@RequestMapping("admin/store")
public class AdminStoreController {
	@Autowired
	StoreService storeService;
	
	@GetMapping("{index}")
	public String User(ModelMap modelMap,@PathVariable("index") String index){
		if(index==null) {
			index="1";
		}
		int indexp = Integer.parseInt(index);
		Page<Store>usPage=storeService.page(indexp-1, 6);
		List<Store>stores=usPage.getContent();
		modelMap.addAttribute("listStore", stores);
		modelMap.addAttribute("page", usPage);
		return "admin/Table/store";
	}
}
