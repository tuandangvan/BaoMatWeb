package com.webproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.webproject.SpringBootWebApplication;
import com.webproject.entity.Category;
import com.webproject.entity.Product;
import com.webproject.entity.Store;
import com.webproject.entity.Style;
import com.webproject.entity.StyleValue;
import com.webproject.entity.User;
import com.webproject.service.CategoryService;
import com.webproject.service.ProductService;
import com.webproject.service.StorageService;
import com.webproject.service.StoreService;
import com.webproject.service.StyleService;
import com.webproject.service.StyleValueService;

@Controller
@RequestMapping("vendor/store/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StyleService styleService;
	
	@Autowired
	private StyleValueService styleValueService;

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

	@RequestMapping("")
	public String getAll(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/account/login";
		Store store = storeService.findByOwnerId(user.get_id());
		List<Product> result = productService.findAllByStoreId(store.get_id());
		List<Category> listCates = categoryService.findAll();
		Optional<Category> category = categoryService.findById(1L);
//		List<Style> listStyles = styleService.findByCategoryId(category.get());
		List<StyleValue> listStyleValues = new ArrayList<>();

		model.addAttribute("listProducts", result);
		model.addAttribute("listCates", listCates);
//		model.addAttribute("listStyles", listStyles);
		model.addAttribute("listStyleValues", listStyleValues);
		return "vendor/product/tables";
	}

	/*
	 * @GetMapping("create") public String create(Model model) { Product product =
	 * new Product(); product.setPromotionalPrice(0); product.setQuantity(0);
	 * product.setIsActive(Boolean.TRUE); product.setIsSelling(Boolean.TRUE);
	 * model.addAttribute("product", product); return
	 * "vendor/product/createProduct"; }
	 */

	@PostMapping("create")
	public String createProduct(Model model, @Valid @ModelAttribute("product") Product product,
			@RequestParam("cateId") Long cateId, @RequestParam("listImagesFile") MultipartFile[] listImages,
			BindingResult result) {
		if (result.hasErrors()) {
			return "vendor/product/createProduct";
		}
		User user = (User) session.getAttribute("user");
		Store store = storeService.findByOwnerId(user.get_id());
		product.setStoreId(store);

		Optional<Category> cate = categoryService.findById(cateId);
		product.setCategoryId(cate.get());

		if (!listImages[0].isEmpty()) {
			String[] temp = new String[listImages.length];
			int index = 0;
			for (MultipartFile x : listImages) {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				temp[index] = storageService.getStorageFilename(x, uuString);
				index++;
			}
			product.setListImages(temp);
			storageService.store(listImages, product.getListImages());
		}
		productService.save(product);
		model.addAttribute("message", "Tạo thành công");
		return "redirect:/vendor/store/product";
	}

	/*
	 * @GetMapping("edit/{productId}") public String edit(Model model, HttpSession
	 * session, @PathVariable("productId") Long id) { User temp = (User)
	 * session.getAttribute("user"); Optional<Product> opt =
	 * productService.findById(id); //Thêm điều kiện là phải cùng của store đó thì
	 * mới get được if (opt.isPresent() ) { Product product = opt.get();
	 * model.addAttribute("product", product); //return
	 * "vendor/product/editProduct"; return "vendor/product/editProduct"; }
	 * model.addAttribute("message", "Product không tồn tại");
	 * 
	 * return "forward:vendor/product"; }
	 */

	@PostMapping("edit/{id}")
	public String update(Model model, @Valid @ModelAttribute("product") Product product, @PathVariable("id") Long id,
			@RequestParam(required = false, name = "cateId") Long cateId,
			@RequestParam("listImagesFile") MultipartFile[] listImagesFile, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return "vendor/store/editStore";
		}

		product.set_id(id);
		if (cateId != null)
			product.setCategoryId(categoryService.findById(cateId).get());

		if (!listImagesFile[0].isEmpty()) {
			String[] temp = new String[listImagesFile.length];
			int index = 0;
			for (MultipartFile x : listImagesFile) {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				temp[index] = storageService.getStorageFilename(x, uuString);
				index++;
			}
			product.setListImages(temp);
			storageService.store(listImagesFile, product.getListImages());
		}
		productService.save(product);

		model.addAttribute("message", "Chỉnh sửa thành công");
		return "redirect:/vendor/store/product";
	}

	@GetMapping("delete")
	public String delete(Model model, @RequestParam(name = "id") Long id) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/account/login";

		Optional<Product> opt = productService.findById(id);
		if (opt.isPresent()) {
			Product product = opt.get();
			product.setStoreId(null);
			productService.save(product);
		}
		model.addAttribute("message", "Xóa thành công");
		return "forward:/vendor/store/product";
	}

	@PostMapping("add-style-value")
	public void addStyleValue(Model model, @ModelAttribute("listStyleValues") List<StyleValue> list,
			@RequestParam("cateId") Long cateId) {
		Optional<Category> category = categoryService.findById(1L);
		List<Style> listStyles = styleService.findByCategoryId(category.get());
		
		for (int i = 0; i < listStyles.size(); i++) {
			StyleValue temp = list.get(i);
			temp.setStyleId(listStyles.get(i));
			styleValueService.save(temp);
		}
		
		Product product = (Product) model.getAttribute("product");
		product.setStyleValueIds(list);
		model.addAttribute("product", product);
	}
}
