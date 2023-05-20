package com.webproject.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.webproject.entity.Order;
import com.webproject.entity.OrderItem;
import com.webproject.entity.Product;
import com.webproject.entity.Store;
import com.webproject.entity.User;
import com.webproject.service.OrderItemService;
import com.webproject.service.OrderService;
import com.webproject.service.ProductService;
import com.webproject.service.StorageService;
import com.webproject.service.StoreService;

@Controller
@RequestMapping("vendor/store")
public class StoreController {
	@Autowired
	private StoreService storeService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	HttpSession session;

	@GetMapping("images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("")
	public String getStore(Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/account/login";
		Store store = storeService.findByOwnerId(user.get_id());
		if (store == null)
			return "redirect:/vendor/store/create";
		model.addAttribute("store", store);
		return "vendor/store/profile";
	}

	@GetMapping("create")
	public String createStore(ModelMap model) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/account/login";
		Store temp = storeService.findByOwnerId(user.get_id());
		if (temp != null)
			return "redirect:/vendor/store/";
		Store store = new Store();
		store.setIsActive(false);
		store.setIsOpen(false);
		model.addAttribute("store", store);
		return "vendor/store/createStore";
	}

	@PostMapping("create")
	public String create(Model model, @Valid @ModelAttribute("store") Store store,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("featuredImagesFile") MultipartFile[] featuredImagesFile,
			@RequestParam("coverFile") MultipartFile coverFile, BindingResult result) {

		if (result.hasErrors()) {
			return "vendor/store/createStore";
		}

		User user = (User) session.getAttribute("user");
		store.setOwnerId(user);

		if (!avatarFile.isEmpty()) { // if (true) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			store.setAvatar(storageService.getStorageFilename(avatarFile, uuString));
			storageService.store(avatarFile, store.getAvatar());
		}

		if (!coverFile.isEmpty()) { // if (true) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			store.setCover(storageService.getStorageFilename(coverFile, uuString));
			storageService.store(coverFile, store.getCover());
		}

		if (!featuredImagesFile[0].isEmpty()) { // if (true) {
			String[] temp = new String[featuredImagesFile.length];
			int index = 0;
			for (MultipartFile x : featuredImagesFile) {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				temp[index] = storageService.getStorageFilename(x, uuString);
				index++;
			}
			store.setFeaturedImages(temp);
			storageService.store(featuredImagesFile, store.getFeaturedImages());
		}
		storeService.save(store);
		model.addAttribute("message", "Tạo thành công");
		return "redirect:/vendor/store";
	}

	@PostMapping("update-info")
	public String updateInfoStore(Model model, @Valid @ModelAttribute("store") Store store,
			@RequestParam("_id") Long id, @RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("featuredImagesFile") MultipartFile[] featuredImagesFile,
			@RequestParam("coverFile") MultipartFile coverFile, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return "vendor/store/editStore";
		}

		store.set_id(id);

		if (!avatarFile.isEmpty()) {
			// if (false) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			store.setAvatar(storageService.getStorageFilename(avatarFile, uuString));
			storageService.store(avatarFile, store.getAvatar());
		}

		if (!coverFile.isEmpty()) {
			// if (false) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			store.setCover(storageService.getStorageFilename(coverFile, uuString));
			storageService.store(coverFile, store.getCover());
		}

		if (!featuredImagesFile[0].isEmpty()) {
			String[] temp = new String[featuredImagesFile.length];
			int index = 0;
			for (MultipartFile x : featuredImagesFile) {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				temp[index] = storageService.getStorageFilename(x, uuString);
				index++;
			}
			store.setFeaturedImages(temp);
			storageService.store(featuredImagesFile, store.getFeaturedImages());
		}
		storeService.save(store);
		// storeService.updateStore(store);
		model.addAttribute("message", "Chỉnh sửa thành công");
		return "redirect:/vendor/store";
	}

	/*
	 * @GetMapping("edit") public String edit(Model model) { User temp = (User)
	 * session.getAttribute("user"); Store store =
	 * storeService.findByOwnerId(temp.get_id()); if (store != null) {
	 * model.addAttribute("store", store); return "vendor/store/editStore"; }
	 * model.addAttribute("message", "Store không tồn tại");
	 * 
	 * return "forward:vendor/store/edit"; }
	 */

	@GetMapping("delete")
	public String delete(Model model, @RequestParam(name = "_id") Long id) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/account/login";

		Optional<Store> opt = storeService.findById(id);
		if (opt.isPresent()) {
			Store store = opt.get();
			store.setOwnerId(null);
			storeService.save(store);
		}
		model.addAttribute("message", "Xóa thành công");
		return "redirect:/vendor/store";
	}

	@GetMapping("dashboard")
	public String dashboard(Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/account/login";
		Store store = storeService.findByOwnerId(user.get_id());
		List<Product> result = productService.findAllByStoreId(store.get_id());
		List<Order> order = orderService.findAllByStoreId(store.get_id());
		double total = 0;
		for (Order item : order) {
			total = total + item.getAmountFromUser();
		}
		int countProduct = result.size();
		int countOrder = order.size();
		model.addAttribute("countProduct", countProduct);
		model.addAttribute("countOrder", countOrder);
		model.addAttribute("total", total);
		return "vendor/index";
	}

	@GetMapping("orders")
	public String getOrder(Model model, @RequestParam(name = "status", defaultValue = "0") int statusId) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/account/login";
		Store store = storeService.findByOwnerId(user.get_id());
		if (statusId == 0) {
			List<Order> listOrders = orderService.findAllByStoreId(store.get_id());
			model.addAttribute("status", "Tất cả");
			model.addAttribute("listOrders", listOrders);
		} else if (statusId == 1) {
			List<Order> listOrders = orderService.findAllByStoreIdAndStatus(store.get_id(), "Chưa xác nhận");
			model.addAttribute("status", "Chưa xác nhận");
			model.addAttribute("listOrders", listOrders);
		} else if (statusId == 2) {
			List<Order> listOrders = orderService.findAllByStoreIdAndStatus(store.get_id(), "Đã xác nhận");
			model.addAttribute("status", "Đã xác nhận");
			model.addAttribute("listOrders", listOrders);
		} else if (statusId == 3) {
			List<Order> listOrders = orderService.findAllByStoreIdAndStatus(store.get_id(), "Đang giao");
			model.addAttribute("status", "Đang giao");
			model.addAttribute("listOrders", listOrders);
		} else if (statusId == 4) {
			List<Order> listOrders = orderService.findAllByStoreIdAndStatus(store.get_id(), "Đã nhận hàng");
			model.addAttribute("status", "Đã nhận hàng");
			model.addAttribute("listOrders", listOrders);
		} else {
			List<Order> listOrders = orderService.findAllByStoreIdAndStatus(store.get_id(), "Bị hủy");
			model.addAttribute("status", "Bị hủy");
			model.addAttribute("listOrders", listOrders);
		}

		return "vendor/order/order";
	}

	@GetMapping("orders-item")
	public String getOrderItem(Model model, @RequestParam("id") Long id) {
		List<OrderItem> list = orderItemService.findByOrderId(id);
		model.addAttribute("listOrderItems", list);
		return "vendor/order/detail";
	}

	@PostMapping("orders/edit")
	public String editStatus(Model model, @Valid @ModelAttribute("order") Order order, @RequestParam("id") Long id,
			@RequestParam("status") String status, BindingResult result) {
		if (result.hasErrors()) {
			return "vendor/order/order";
		}
		Optional<Order> old = orderService.findById(id);
		Order newOrder = old.get();
		newOrder.setStatus(status);
		orderService.save(order);

		return "redirect:/vendor/store/orders";

	}

}
