package com.webproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webproject.entity.Order;
import com.webproject.entity.OrderItem;
import com.webproject.entity.User;
import com.webproject.model.UserModel;
import com.webproject.service.OrderItemService;
import com.webproject.service.OrderService;
import com.webproject.service.UserService;

@Controller
@RequestMapping("/account/profile")
public class ProfileController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping("")
	public String profilePage(ModelMap model, HttpSession session) {
		
		return "web/profile";
	}
	
	@GetMapping("/edit")
	public String profileEditPage(ModelMap model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return "redirect:/account/login";
		User entity = userService.findByEmail(user.getEmail());
		
		model.addAttribute("user",entity);
		return "web/editProfile";
	}
	
	@PostMapping("/edit")
	public String profileEdit(ModelMap model,@Valid @ModelAttribute("user") UserModel usermodel, BindingResult result, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return "redirect:/account/login";
		User entity = userService.findByEmail(user.getEmail());
		entity.setFirstName(usermodel.getFirstName());
		entity.setLastName(usermodel.getLastName());
		entity.setIdCard(usermodel.getIdCard());
		entity.setPhone(usermodel.getPhone());
		entity.setAddress(usermodel.getAddress());
		userService.save(entity);
		
		model.addAttribute("user",entity);
		return "web/editProfile";
	}
	
	@GetMapping("/change-password")
	public String changePassPage(ModelMap model, HttpSession session) {
		
		//Gửi token được tạo ngẫu nhiên lên session
		String csrfToken = UUID.randomUUID().toString();
		session.setAttribute("csrfToken", csrfToken);


		User user = (User) session.getAttribute("user");
		if(user == null)
			return "redirect:/account/login";
		
		model.addAttribute("user",user);
		return "web/changePassword";
	}
	@PostMapping("/change-password")
	public String changePassword(ModelMap model,@Valid @ModelAttribute("user") UserModel usermodel, BindingResult result, HttpSession session, HttpServletRequest request) {
		String csrfToken = changePassword(request);
		String storedToken = (String) session.getAttribute("csrfToken");
		 
		if (csrfToken == null || !csrfToken.equals(storedToken)) {
		    // Thông báo không xác thực
			String message = "Không xác thực được người dùng";
			model.addAttribute("messageError", message);
			
		} else {
		    // Mã thông báo CSRF hợp lệ, tiếp tục xử lý yêu cầu đổi mật khẩu
			User user = (User) session.getAttribute("user");
			String message= "";
			if(user == null)
				return "redirect:/account/login";
			
			if(BCrypt.checkpw(usermodel.getCurrentpassword(), user.getHashedPassword())) {
				if(usermodel.getPassword().equals(usermodel.getPassword2())) {
					user.setHashedPassword(BCrypt.hashpw(usermodel.getPassword(), BCrypt.gensalt()));
					userService.save(user);
					message = "Đổi mật khẩu thành công";
					model.addAttribute("messageSuccess",message);
				}
				else {
					message = "Mật khẩu nhập lại không chính xác";
					model.addAttribute("messageError",message);
				}
			}
			else {
				message = "Mật khẩu không chính xác";
				model.addAttribute("messageError",message);
			}
			
			model.addAttribute("user",user);
		}
		return "web/changePassword";
	}
	
	@RequestMapping("/change-password")
	public String changePassword(HttpServletRequest request) {
	    String inputValue = request.getParameter("csrfToken");
	    return inputValue;
	}

	
	@GetMapping("/orders")
	public String listorder(ModelMap model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return "redirect:/account/login";
		List<Order> orders = orderService.findAllByUserId(user.get_id());
		List<OrderItem> orderItems = new ArrayList<>();
		
		for (Order order : orders) {
			List<OrderItem> list = orderItemService.findByOrderId(order.get_id());
			for(OrderItem item : list) {
				orderItems.add(item);
			}
		}
		model.addAttribute("orderitems",orderItems);
		model.addAttribute("orders",orders);
		model.addAttribute("user",user);
		return "web/historyPurchase";
	}
}
