package com.webproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webproject.entity.Order;
import com.webproject.entity.OrderItem;
import com.webproject.service.OrderItemService;
import com.webproject.service.OrderService;
import com.webproject.service.ProductService;

@Controller
@RequestMapping("admin/order")
public class AdminOrderController {
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderItemService orderItemService;

	@GetMapping("{varstatus}/{index}")
	public String listOrder(ModelMap modelMap, @PathVariable("varstatus") Integer var,
			@PathVariable("index") Integer index) {
		if (index == null) {
			index = 1;
		}
		String status = null;
		if (var == 0) {
			status = "Tất cả";
			Page<Order> page = orderService.findAll(index - 1, 6);
			List<Order> orders = page.getContent();
			modelMap.addAttribute("listOrder", orders);
			modelMap.addAttribute("page", page);
		} else {
			if (var == 1) {
				status = "Chưa xác nhận";
			}
			if (var == 2) {
				status = "Đã xác nhận";
			}
			if (var == 3) {
				status = "Đang giao";
			}
			if (var == 4) {
				status = "Đã nhận hàng";
			}
			if (var == 5) {
				status = "Bị hủy";
			}
			Page<Order> page = orderService.findByStatus(status, index - 1, 6);
			List<Order> orders = page.getContent();
			modelMap.addAttribute("listOrder", orders);
			modelMap.addAttribute("page", page);
		}
		modelMap.addAttribute("status", status);

		return "admin/Table/order";
	}

	@GetMapping("{varstatus}/{index}/details/{orderId}")
	public String Details(ModelMap Model, @PathVariable("varstatus") Integer var, @PathVariable("index") Integer index,
			@PathVariable("orderId") Long Id) {
		Optional<Order> optional = orderService.findById(Id);
		Order order = optional.get();
		List<OrderItem> orderItems = orderItemService.findByOrderId(Id);
		Model.addAttribute("order", order);
		Model.addAttribute("listOrderItem", orderItems);
		return "admin/Table/orderItem";
	}

	@GetMapping("{varstatus}/{index}/edit/{id}")
	public ModelAndView edit(ModelMap modelMap, @PathVariable("varstatus") Integer var,
			@PathVariable("index") Integer index, @PathVariable("id") Long id) {
		Optional<Order> optional = orderService.findById(id);
		Order order = optional.get();
		order.setStatus("Đang giao");
		orderService.save(order);
		modelMap.addAttribute("message", "Đã bàn giao cho đơn vị vận chuyển");
		return new ModelAndView("forward:/admin/order/"+var+"/"+index);
	}

}
