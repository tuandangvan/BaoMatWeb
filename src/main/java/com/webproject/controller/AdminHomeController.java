package com.webproject.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webproject.entity.Order;
import com.webproject.entity.User;
import com.webproject.service.OrderItemService;
import com.webproject.service.OrderService;
import com.webproject.service.ProductService;
import com.webproject.service.StoreService;
import com.webproject.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminHomeController {

	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;

	@Autowired
	StoreService storeService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderItemService orderItemService;

	@GetMapping("")
	public String Home(ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/account/login";
		//format date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-DD");
		//count
		Long countUser=userService.Count();
		Long countStore=storeService.count();
		Long countProduct=productService.count();
		Long orderCount=orderService.count();
		modelMap.addAttribute("countUser", countUser);
		modelMap.addAttribute("countStore", countStore);
		modelMap.addAttribute("countProduct", countProduct);
		modelMap.addAttribute("orderCount", orderCount);
		
		//Revenue
		double revenueTotat=0;
		List<Order>orders=orderService.findAllSortDate();
		List<Order>orders2 = new ArrayList<Order>();
		List<Double>dateRevenue=new ArrayList<Double>();
		List<Date>orderDates=new ArrayList<Date>();
		orders2.add(orders.get(0));
		int index=0;
		for (Order order : orders) {
			revenueTotat=revenueTotat+order.getAmountFromUser();
			if(dateFormat.format(order.getCreatedAt())!=dateFormat.format(orders2.get(index).getCreatedAt())){
				orders2.add(order);
			}
			index=index+1;
		}
		for (Order order : orders2) {
			List<Order>ordersByDate=orderService.findAllByDate(dateFormat.format(order.getCreatedAt()));
			double revenueDate=0;
			for (Order order1 : ordersByDate) {
				revenueDate=revenueDate+order1.getAmountFromUser();
			}
			dateRevenue.add(revenueDate);
		}
		System.out.print(orders2.get(0).getCreatedAt());
//		System.out.print(orderDates.get(0));
		modelMap.addAttribute("revenueTotat", revenueTotat);
		modelMap.addAttribute("orders", orders2);
		modelMap.addAttribute("dateRevenueTotal", dateRevenue);
		
		
		//Product
		
		//Order
		
		
		
		//User
		
		return "admin/home";
	}
}
