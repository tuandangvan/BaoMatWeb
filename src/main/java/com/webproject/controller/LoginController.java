package com.webproject.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.bcel.Const;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webproject.entity.User;
import com.webproject.model.UserModel;
import com.webproject.service.UserService;

@Controller
@RequestMapping("account")
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping("login")
	public String loginPage(ModelMap model) {
		UserModel user = new UserModel();
		model.addAttribute("user", user);
		model.addAttribute("action", "login");
		return "login/login";
	}
	@PostMapping("login")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("user") UserModel user, BindingResult result, HttpSession session) throws JSONException
	{	
		String message = "";
		if(result.hasErrors()) {	
			return new ModelAndView("login/login");
		}
		user.setEmail(user.getEmail().trim());
		user.setPassword(user.getPassword().trim());
		if(user.getEmail() == "" || user.getPassword() == "") {
			model.addAttribute("user", user);
			model.addAttribute("messageError", "dữ liệu nhập vào không được để trống!");
			return new ModelAndView("login/login");
		}
		User entity =  userService.findByEmail(user.getEmail()) ;
		
		if(entity == null) {
			message = "không tìm thấy tài khoản";
		}
		else if(BCrypt.checkpw(user.getPassword(), entity.getHashedPassword())) {
			
			session.setAttribute("user", entity);
			if(entity.getRoles().equals("admin") ) {
				return new ModelAndView("redirect:/admin");
			}
			return new ModelAndView("redirect:/");
		}
		else {
			message = "Mật khẩu không chính xác";
		}
		model.addAttribute("user", user);
		model.addAttribute("messageError", message);
		return new ModelAndView("login/login");
	}
	
	@GetMapping("signup")
	public String signUpPage(ModelMap model) {
		UserModel user = new UserModel();
		model.addAttribute("user", user);
		model.addAttribute("action", "signup");
		return "login/login";
	}
	@PostMapping("signup")
	public ModelAndView signUp(ModelMap model, @Valid @ModelAttribute("user") UserModel user, BindingResult result) {
		String message="";
		
		user.setEmail(user.getEmail().trim());
		user.setFirstName(user.getFirstName().trim());
		user.setLastName(user.getLastName().trim());
		user.setIdCard(user.getIdCard().trim());
		user.setPhone(user.getPhone().trim());
		user.setPassword(user.getPassword().trim());
		user.setPassword2(user.getPassword2().trim());
		user.setRoles("user");
		
		if(!user.getPassword().equals(user.getPassword2())) {
			System.err.println(!user.getPassword().toString().equals(user.getPassword2().toString()));
			message = "mật khẩu nhập lại không chính xác";
		}
		else if(user.getEmail() == "" || user.getFirstName() == "" || user.getLastName() == "" || user.getIdCard() == "" 
				|| user.getPhone() == "" || user.getPassword() == "") {
			message = "dữ liệu nhập vào không được để trống!";
		}
		else {
			//trungf 
			
			
			try {
				User userResp = new User();
				BeanUtils.copyProperties(user, userResp);
				userResp.setHashedPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
				userService.save(userResp);
				model.addAttribute("messageSuccess", "Đăng ký thành công vui lòng trở lại đăng nhập");
				model.addAttribute("action", "signup");
				model.remove("user");
				return new ModelAndView("login/login");
			}
			catch (Exception e) {
				
			}
			
		}
		model.addAttribute("user", user);
		model.addAttribute("messageError", message);
		model.addAttribute("action", "signup");
		return new ModelAndView("login/login");
		
	}
	
	@GetMapping("logout")
	public String logout(ModelMap model, HttpSession session) {
		session.invalidate();
		return "redirect:/account/login";
	}
	
}
