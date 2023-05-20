package com.webproject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.webproject.entity.Category;
import com.webproject.entity.Product;
import com.webproject.entity.Store;
import com.webproject.entity.User;
import com.webproject.service.CartItemService;
import com.webproject.service.CartService;
import com.webproject.service.CategoryService;
import com.webproject.service.ProductService;
import com.webproject.service.StorageService;
import com.webproject.service.StoreService;

@Controller
@RequestMapping("")
public class WebController {
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private CategoryService cateService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private ProductService productService;

	@GetMapping("images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	@GetMapping("")
	public String homePage(ModelMap model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Product> list = productService.findLastestProduct();
		
		model.addAttribute("page", "home");
		model.addAttribute("productlist", list);
		return "web/trangchu";
	}
	
	@GetMapping("category-list")
	public String categoryPage(ModelMap model, HttpSession session) {
		//User user = (User) session.getAttribute("user");
		List<Category> categories = cateService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("page", "category");
		return "web/CategoryList";
	}
	
	@GetMapping("category-list/{categoryslug}")
	public String productByCate(ModelMap model,@PathVariable String categoryslug, HttpSession session) {
		Category cate = cateService.findBySlug(categoryslug);
		List<Product> list = productService.findAllByCategoryId(cate.get_id());
		model.addAttribute("cate",cate);
		model.addAttribute("list",list);
		
		return "web/ketquatimkiem";
	}
	
	@GetMapping("store/{id}")
	public String getMethodName(Model model, @PathVariable Long id) {
		Optional<Store> opt = storeService.findById(id);
		List<Product> list = productService.findAllByStoreId(opt.get().get_id());
		model.addAttribute("store", opt.get());
		model.addAttribute("listProducts", list);
		return "vendor/InfoStore";
	}
	
	@PostMapping("search")
	public String search(Model model, HttpServletRequest req) {
		String searchKey = req.getParameter("search-key");
		String option = req.getParameter("option");
		
		if(option.equals("product")) {
			List<Product> products = productService.searchProductByName("%"+searchKey+"%");
			if(products.size() > 0)
				model.addAttribute("list",products);
		}
		else if(option.equals("category")) {
			List<Category> categories = cateService.searchCategoryByName("%"+searchKey+"%");
			if(categories.size() > 0)
				model.addAttribute("categories",categories);
		}
		else if(option.equals("store")) {
			System.err.println("s");
		}
		model.addAttribute("option",option);
		model.addAttribute("search-key",searchKey);
		return "web/ketquatimkiem";
	}
	
	@GetMapping("/product/{id}")
	public String productDetail(ModelMap model,@PathVariable Long id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Product product = productService.findById(id).get();
		
		model.addAttribute("product",product);
		return "web/productDetail";
	}
	
}
