package com.webproject.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webproject.entity.Category;
import com.webproject.model.CategoryModel;
import com.webproject.service.CategoryService;
import com.webproject.service.StorageService;



@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {
	@Autowired
	CategoryService categoryService;
	
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
	@RequestMapping("")
	public String list(ModelMap model) {

		List<Category> list = categoryService.findAll();

		model.addAttribute("categories", list);

		return "admin/Table/category";

	}
	
	@GetMapping("add")
	public String create(ModelMap model) {

		CategoryModel cate = new CategoryModel();
		cate.setIsEdit(false);
		model.addAttribute("category", cate);

		return "admin/Table/add_category";

	} 
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryModel cate,
			HttpServletRequest request,BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/Table/add_category");
		}
		//xử lí hình ảnh
		if(!cate.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			//Lưu file vào trường image
			cate.setImage(storageService.getStorageFilename(cate.getImageFile(), uuString));
			storageService.store(cate.getImageFile(),cate.getImage());
		}
		Category category =new Category();
		
		//Copy tu Model sang Entity
		BeanUtils.copyProperties(cate, category);
		if(cate.getIsEdit()==true) {
			category.set_id(Long.parseLong(request.getParameter("_id")));
			if(cate.getImageFile().isEmpty()) {
				category.setImage(cate.getImage());
			}
		}
		categoryService.save(category);
		String message="";
		if(cate.getIsEdit()==true) {
			message="Category cap nhat thanh cong";
			
		}else {
			message="Category duoc them thanh cong";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/category",model);
	}
	@GetMapping("edit/{_id}")
	public ModelAndView edit(ModelMap model,@PathVariable("_id") Long Cate_id) {
		Optional<Category> optional=categoryService.findById(Cate_id);
		
		CategoryModel categoryModel=new CategoryModel();
		if(optional.isPresent()) {
			Category category=optional.get();
			BeanUtils.copyProperties(category, categoryModel);
			categoryModel.setIsEdit(true);
			model.addAttribute("category", categoryModel);
			return new ModelAndView("admin/Table/add_category", model);
		}
		model.addAttribute("message", "Category không tồn tại");
		return new ModelAndView("forward:/admin/category",model);
	}
	
	@GetMapping("delete/{_id}")
	public ModelAndView delete(ModelMap model,@PathVariable("_id") Long Cate_id) {
		categoryService.deleteById(Cate_id);
		
		model.addAttribute("message", "Category đã được xóa thành công");
		
		return new ModelAndView("forward:/admin/category",model);
	}
	
}
