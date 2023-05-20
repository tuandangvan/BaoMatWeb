package com.webproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webproject.entity.Product;
import com.webproject.service.ProductService;
import com.webproject.service.StorageService;

@Controller
@RequestMapping("admin/product")
public class AdminProductController {
	@Autowired
	ProductService productService;
	@Autowired
	private StorageService storageService;

	@GetMapping("images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("all/{index}")
	public String User(ModelMap modelMap, @PathVariable("index") String index) {
		if (index == null) {
			index = "1";
		}
		int indexp = Integer.parseInt(index);
		Page<Product> usPage = productService.page(indexp - 1, 6);
		List<Product> products = usPage.getContent();
		modelMap.addAttribute("listProducts", products);
		modelMap.addAttribute("page", usPage);
		return "admin/Table/products";
	}

}
