package com.webproject.controller;

import java.lang.reflect.Field;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("login")
	public String loginPage(ModelMap model, HttpServletResponse response) {
		response.setHeader("X-Frame-Options", "DENY");
		UserModel user = new UserModel();
		model.addAttribute("user", user);
		model.addAttribute("action", "login");
		return "login/login";
	}
	@PostMapping("login")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("user") UserModel user, BindingResult result, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws JSONException
	{	
		response.setHeader("X-Frame-Options", "DENY");
		String message = "";
		if(result.hasErrors()) {	
			return new ModelAndView("login/login");
		}
		
		if(user.getEmail() == null || user.getPassword() == null) {
			model.addAttribute("messageError", "input không hợp lệ");
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
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		    Authentication authentication = authenticationManager.authenticate(token);
		    SecurityContextHolder.getContext().setAuthentication(authentication);
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("JSESSIONID".equals(cookie.getName())) {

                        // Set the SameSite attribute
                        cookie.setSecure(true); // Only send the cookie over a secure channel (HTTPS)
                        cookie.setHttpOnly(true);
            
            			// Thiết lập thuộc tính SameSite bằng cách thiết lập tiêu đề Set-Cookie thủ công
            			String sameSiteAttribute = "SameSite=Strict";
            			String cookieWithSameSite = cookie.getName() + "=" + cookie.getValue() + "; " + sameSiteAttribute;
            			response.setHeader("Set-Cookies", cookieWithSameSite);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        
                        break;
                    }
                }
            }
			
			session.setAttribute("user", entity);
			
			if(entity.getRoles().equals("admin") ) {
				return new ModelAndView("redirect:/admin");
			}
			if(authentication.isAuthenticated())
				return new ModelAndView("redirect:/");
			return new ModelAndView("login/login");
		}
		else {
			message = "Mật khẩu không chính xác";
		}
		model.addAttribute("user", user);
		model.addAttribute("messageError", message);
		return new ModelAndView("login/login");
	}
	
	@GetMapping("signup")
	public String signUpPage(ModelMap model, HttpServletResponse response) {
		response.setHeader("X-Frame-Options", "DENY");
		UserModel user = new UserModel();
		model.addAttribute("user", user);
		model.addAttribute("action", "signup");
		return "login/login";
	}
	@PostMapping("signup")
	public ModelAndView signUp(ModelMap model, @Valid @ModelAttribute("user") UserModel user, BindingResult result, HttpServletResponse response) {
		
		response.setHeader("X-Frame-Options", "DENY");
		String message="";
		
		user.setEmail(user.getEmail().trim());
		user.setFirstName(user.getFirstName().trim());
		user.setLastName(user.getLastName().trim());
		user.setIdCard(user.getIdCard().trim());
		user.setPhone(user.getPhone().trim());
		user.setPassword(user.getPassword().trim());
		user.setPassword2(user.getPassword2().trim());
		user.setRoles("user");
		
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		
		if(!validatePassword(user.getPassword()).equals("")) {
			message = validatePassword(user.getPassword());
		}
		else if(!BCrypt.checkpw(user.getPassword2(), hashedPassword)) {
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
				userResp.setHashedPassword(passwordEncoder.encode(user.getPassword()));
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
	public String logout(ModelMap model, HttpSession session, HttpServletResponse response) {
		session.invalidate();
		return "redirect:/account/login";
	}
	
	public String validatePassword(String password) {
	    String message = "";

	    // Kiểm tra độ dài mật khẩu
	    if (password.length() < 8) {
	        message += "Mật khẩu phải có ít nhất 8 kí tự.\n";
	    }

	    // Kiểm tra chữ hoa
	    if (!password.matches(".*[A-Z].*")) {
	        message += "Mật khẩu phải chứa ít nhất một chữ hoa.\n";
	    }

	    // Kiểm tra chữ thường
	    if (!password.matches(".*[a-z].*")) {
	        message += "Mật khẩu phải chứa ít nhất một chữ thường.\n";
	    }

	    // Kiểm tra số
	    if (!password.matches(".*\\d.*")) {
	        message += "Mật khẩu phải chứa ít nhất một số.\n";
	    }

	    // Kiểm tra kí tự đặc biệt
	    if (!password.matches(".*[@#$%^&+=].*")) {
	        message += "Mật khẩu phải chứa ít nhất một kí tự đặc biệt (@, #, $, %, ^, &, +, =).\n";
	    }
	    return message;
	}
	
}
