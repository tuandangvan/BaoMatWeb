package com.webproject.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webproject.entity.Category;
import com.webproject.entity.Style;
import com.webproject.entity.StyleValue;
import com.webproject.service.CategoryService;
import com.webproject.service.StyleService;
import com.webproject.service.StyleValueService;

@Controller
@RequestMapping("admin/style")
public class AdminStyleController {

	@Autowired
	StyleService styleService;

	@Autowired
	StyleValueService styleValueService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping("{cateId}/{index}")
	public String ListStyle(ModelMap modelMap, @PathVariable("cateId") Long cateId,
			@PathVariable("index") String index) {
		if (index == null) {
			index = "1";
		}
		int indexp = Integer.parseInt(index);
		List<Category> categories = categoryService.findAll();
		modelMap.addAttribute("categories", categories);
		if (cateId == 0) {
			Page<Style> usPage = styleService.page(indexp - 1, 6);
			List<Style> styles = usPage.getContent();
			modelMap.addAttribute("listStyles", styles);
			modelMap.addAttribute("page", usPage);
		} else {
			Optional<Category> optional = categoryService.findById(cateId);
			Category category = optional.get();
			Page<Style> usPage = styleService.pageFiterbyCate(category.get_id(), indexp - 1, 6);
			List<Style> styles = usPage.getContent();
			modelMap.addAttribute("category", category);
			modelMap.addAttribute("listStyles", styles);
			modelMap.addAttribute("page", usPage);
		}

		return "admin/Table/style";
	}

	@GetMapping("add")
	public String create(ModelMap model) {

		Style style = new Style();
		List<Category> categories = categoryService.findAll();
		model.addAttribute("style", style);
		model.addAttribute("categories", categories);
		return "admin/Table/add_style";

	}

	@PostMapping("save")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("style") Style style, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/Table/add_style");
		}
		styleService.save(style);

		model.addAttribute("message", "Đã lưu thành công");
		return new ModelAndView("forward:/admin/style/0/1", model);
	}
	@PostMapping("edit")
	public ModelAndView edit(ModelMap model, @Valid @ModelAttribute("style") Style style, @RequestParam("_id") String id ,BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/Table/add_style");
		}
		style.set_id(Long.parseLong(id));
		styleService.save(style);

		model.addAttribute("message", "Đã lưu thành công");
		return new ModelAndView("forward:/admin/style/0/1", model);
	}

	@GetMapping("edit/{_id}")
	public ModelAndView edit(ModelMap model, @PathVariable("_id") Long id) {
		Optional<Style> optional = styleService.findById(id);
		if (optional.isPresent()) {
			Style style = optional.get();
			List<Category> categories = categoryService.findAll();
			model.addAttribute("categories", categories);
			model.addAttribute("style", style);
			return new ModelAndView("admin/Table/add_style", model);
		}
		model.addAttribute("message", "Style không tồn tại");
		return new ModelAndView("forward:/admin/style/0/1", model);
	}

	@GetMapping("delete/{_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("_id") Long id) {
		Optional<Style> optional = styleService.findById(id);
		Style style = optional.get();
		styleService.delete(style);
		model.addAttribute("message", "Style đã được xóa thành công");
		return new ModelAndView("forward:/admin/style/0/1", model);
	}

	// STYLE VALUE

	@GetMapping("value/{styleId}")
	public String listStyleValue(ModelMap modelMap, @PathVariable("styleId") Long id) {
		Optional<Style> optional = styleService.findById(id);
		Style style = optional.get();
		List<StyleValue> styleValueys = styleValueService.findByStyleId(style);
		modelMap.addAttribute("style", style);
		modelMap.addAttribute("ListstyleValue", styleValueys);
		return "admin/Table/styleValue";
	}

	@PostMapping("value/{styleId}/save")
	public ModelAndView save(ModelMap model, @PathVariable("styleId") String id,
			@ModelAttribute("styleValue") StyleValue styleValue, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/Table/styleValue");
		}
		styleValueService.save(styleValue);
//		System.out.println("AdminStyleController.save()");
//		Long styleId=Long.parseLong(id);
//		Optional<Style>optional=styleService.findById(styleId);
//		Style style=optional.get();
//		List<StyleValue>styleValueys=styleValueService.findByStyleId(style);
//		model.addAttribute("style", style);
//		model.addAttribute("ListstyleValue", styleValueys);
//		model.addAttribute("message", "Thêm thành công");
		return new ModelAndView("redirect:/admin/style/value/" + id, model);
	}

	@GetMapping("value/{styleId}/delete/{_id}")
	public ModelAndView deleteStyleValue(ModelMap model, @PathVariable("styleId") String styleid,
			@PathVariable("_id") Long id) {
		Optional<StyleValue> optional = styleValueService.findById(id);
		StyleValue styleValue = optional.get();
		styleValueService.delete(styleValue);
//		model.addAttribute("message", "Đã xóa thành công");
		return new ModelAndView("redirect:/admin/style/value/" + styleid, model);
	}
}
