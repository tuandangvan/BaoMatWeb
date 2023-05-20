package com.webproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.webproject.entity.Category;
import com.webproject.entity.Product;
import com.webproject.entity.Store;
import com.webproject.repository.ProductRepo;
import com.webproject.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepo productRepo;

	@Override
	public <S extends Product> S save(S entity) {
		if (entity.get_id() != null) {
			Optional<Product> opt = findById(entity.get_id());
			if (opt.isPresent()) {
				if (StringUtils.isEmpty(entity.getListImages())) {
					entity.setListImages(opt.get().getListImages());
				} else {
					entity.setListImages(entity.getListImages());
				}
				if (entity.getStoreId() == null) {
					entity.setStoreId(opt.get().getStoreId());
				}
				if (entity.getCategoryId() == null) {
					entity.setCategoryId(opt.get().getCategoryId());
				}
				if(entity.getCreatedAt() == null) {
					entity.setCreatedAt(opt.get().getCreatedAt());
				}
			}
		}
		return productRepo.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepo.findAll(pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepo.findAll(sort);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepo.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		productRepo.deleteById(id);
	}

	@Override
	public List<Product> findAllByStoreId(Long storeId) {
		return productRepo.findAllByStoreId(storeId);
	}

	@Override
	public long count() {
		return productRepo.count();
	}

	@Override
	public Page<Product> page(int index, int size) {
		// TODO Auto-generated method stub
		return productRepo.findAll(PageRequest.of(index, size));
	}
	
	@Override
	public List<Product> findLastestProduct() {
		return productRepo.findLastestProduct();
	}

	@Override
	public List<Product> findAllByCategoryId(Long categoryId) {
		return productRepo.findAllByCategoryId(categoryId);
	}

	@Override
	public List<Product> searchProductByName(String keyword) {
		return productRepo.searchProductByName(keyword);
	}
	
	
}
