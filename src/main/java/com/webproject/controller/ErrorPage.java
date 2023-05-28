package com.webproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorPage {
	
	@GetMapping("permission")
	public String permissionError(ModelMap model) {
		model.addAttribute("message", "Bạn không có quyền truy cập vào tài nguyên này!");
		return "errorpage";
	}
}
