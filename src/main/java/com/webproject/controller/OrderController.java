package com.webproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webproject.entity.Cart;
import com.webproject.entity.CartItem;
import com.webproject.entity.Order;
import com.webproject.entity.OrderItem;
import com.webproject.entity.User;
import com.webproject.model.OrderModel;
import com.webproject.service.CartItemService;
import com.webproject.service.CartService;
import com.webproject.service.DeliveryService;
import com.webproject.service.OrderItemService;
import com.webproject.service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("add")
	public ModelAndView MakeOrder(ModelMap model,  @Valid @ModelAttribute("cartitem") List<Long> cartItem,@Valid @ModelAttribute("order") OrderModel order, BindingResult result, HttpSession session) throws JSONException
	{	
		User user = (User) session.getAttribute("user");
		
		
		List<CartItem> cartItems = new ArrayList<>(); 
		cartItem.forEach((n) -> cartItems.add(cartItemService.findById(n).get()));
		
		while(!cartItems.isEmpty()) {
			CartItem entity = cartItems.get(0);
			
			Order orderEntity =  new Order();
			BeanUtils.copyProperties(order, orderEntity);
			orderEntity.setUserId(user);
			orderEntity.setStoreId(entity.getCartId().getStoreId());
			orderEntity.setStatus("Chưa xác nhận");
			orderEntity.setDeliveryId(deliveryService.findById(order.getDelivery()).get());
			orderEntity.setAmountFromUser(orderEntity.getAmountFromUser() + orderEntity.getDeliveryId().getPrice());
			orderService.save(orderEntity);
			
			List<CartItem> removeList = new ArrayList<>();
			for (CartItem cartItemEntity : cartItems) {
				if(cartItemEntity.getCartId().getStoreId() == orderEntity.getStoreId()) {
					OrderItem orderItemEntity = new OrderItem();
					orderItemEntity.setOrderId(orderEntity);
					orderItemEntity.setProductId(cartItemEntity.getProductId());
					orderItemEntity.setCount(cartItemEntity.getCount());
					orderItemService.save(orderItemEntity);
					orderEntity.setAmountFromUser(orderEntity.getAmountFromUser() + orderItemEntity.getCount()*orderItemEntity.getProductId().getPrice());
					//cartItems.remove(cartItemEntity);
					removeList.add(cartItemEntity);
				}
			}
			
			//remove
			for (CartItem cartItemRemove : removeList) {
				cartItems.remove(cartItemRemove);
				cartItemService.delete(cartItemRemove);
				
				Cart check = cartService.findById(cartItemRemove.getCartId().get_id()).get();
				if(cartItemService.findCartItemByCartId(check.get_id()).isEmpty() ) {
					cartService.delete(check);
				}
			}
			
		}
		
		return new ModelAndView("redirect:/cart");
	}
	
}
