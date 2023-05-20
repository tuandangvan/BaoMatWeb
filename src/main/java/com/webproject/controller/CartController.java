package com.webproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webproject.entity.Cart;
import com.webproject.entity.CartItem;
import com.webproject.entity.Delivery;
import com.webproject.entity.Product;
import com.webproject.entity.User;
import com.webproject.service.CartItemService;
import com.webproject.service.CartService;
import com.webproject.service.DeliveryService;
import com.webproject.service.ProductService;

@Controller
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartitemService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping("")
	public String cartPage(ModelMap model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Long> cartId = cartService.getAllCartIdOfUser(user.get_id());
		List<List<CartItem>> cartItem = new ArrayList<>();
		cartId.forEach((n) -> cartItem.add(cartitemService.findCartItemByCartId(n)));
		List<Delivery> deliveries = deliveryService.findAll();
		model.addAttribute("deliveries", deliveries);
		model.addAttribute("cartItem", cartItem);
		model.addAttribute("page", "cart");
		return "web/Cart";
	}
	//dùng để gọi từ ajax
	@PostMapping("add-to-cart")
	public ResponseEntity<?>  addToCart(@Valid @RequestBody Long pid, HttpSession session) throws Exception {
		Cart cart;
		CartItem cartItem;
		User user = (User) session.getAttribute("user");
		System.err.println(user);
		Product product = productService.findById(pid).get();
		
		Optional<Cart> opt = cartService.findCartByUserIdAndStoreId(user.get_id(), product.getStoreId().get_id());
		if(!opt.isPresent()) {
			System.err.println("empty");
			cart = new Cart();
			cart.setUserId(user);
			cart.setStoreId(product.getStoreId());
			cartService.save(cart);
		}
		else {
			System.err.println("present");
			cart = opt.get();
		}
		
		Optional<CartItem> cartItemOpt = cartitemService.findCartItemByCartIdAndProductId(cart.get_id(), product.get_id());
		if(!cartItemOpt.isPresent()) {
			System.err.println("empty");
			cartItem= new CartItem();
			cartItem.setCartId(cart);
			cartItem.setCount(1);
			cartItem.setProductId(product);
			cartitemService.save(cartItem);
		}
		else {
			System.err.println("present");
			cartItem = cartItemOpt.get();
			cartItem.setCount(cartItem.getCount() + 1);
			cartitemService.save(cartItem);			
		}
		
		return ResponseEntity.ok("thanhf coong");

	}
	
	@PostMapping("delete")
	public ModelAndView DeleteCartItem(ModelMap model,  @Valid @ModelAttribute("cartitem") List<Long> cartItem, BindingResult result) throws JSONException
	{	
		System.err.println(cartItem);
		List<CartItem> cartItems = new ArrayList<>(); 
		cartItem.forEach((n) -> cartItems.add(cartitemService.findById(n).get()));
		
		for (CartItem cartItemEntity : cartItems) {
			Cart cart = cartItemEntity.getCartId();
			cartitemService.delete(cartItemEntity);
			if(cartitemService.findCartItemByCartId(cart.get_id()).isEmpty() ) {
				cartService.delete(cart);
			}
		}
		
		return new ModelAndView("redirect:/cart");
	}
	
	
	@PostMapping("get-quantity-cartitem")
	public ResponseEntity<?>  getQuantityCartItem(@Valid @RequestBody Long cartitemid) throws Exception {
		int quantity = cartitemService.findById(cartitemid).get().getCount();
		return ResponseEntity.ok(quantity);
	}
	
	@PostMapping("change-quantity-cartitem")
	public ResponseEntity<?>  changeQuantityCartItem(@Valid @RequestBody CartItem cartitem) throws Exception {
		CartItem entity = cartitemService.findById(cartitem.get_id()).get();
		entity.setCount(cartitem.getCount());
		cartitemService.save(entity);
		return ResponseEntity.ok(0);
	}
	
	/*
	 * @PostMapping("get-total-price") public ResponseEntity<?>
	 * getToTalPrice(@Valid @RequestBody List<Long> cartItem) throws Exception {
	 * double total = 0; List<CartItem> cartItems = new ArrayList<>();
	 * cartItem.forEach((n) -> cartItems.add(cartitemService.findById(n).get()));
	 * for (CartItem cartItemEntity : cartItems) { total = total +
	 * cartItemEntity.getCount()*cartItemEntity.getProductId().getPrice(); } return
	 * ResponseEntity.ok(total); }
	 */
	
	@PostMapping("get-total-price")
	public ResponseEntity<?>  getToTalPrice(@Valid @RequestParam("cartItem[]") List<Long> cartItem,@Valid @RequestParam(required = false) Long deliveryId ) throws Exception {
		double total = 0;
		System.err.println(cartItem);
		List<CartItem> cartItems = new ArrayList<>(); 
		cartItem.forEach((n) -> cartItems.add(cartitemService.findById(n).get()));
		for (CartItem cartItemEntity : cartItems) {
			total = total + cartItemEntity.getCount()*cartItemEntity.getProductId().getPrice();
		}
		if(deliveryId != null) {
			Delivery deliveryentity = deliveryService.findById(deliveryId).get();
			total = total + deliveryentity.getPrice();
		}
		return ResponseEntity.ok(total);
	}
}
