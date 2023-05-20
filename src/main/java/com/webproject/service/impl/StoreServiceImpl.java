package com.webproject.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.webproject.entity.Store;
import com.webproject.repository.StoreRepo;
import com.webproject.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepo storeRepo;

	public StoreServiceImpl(StoreRepo storeRepo) {
		this.storeRepo = storeRepo;
	}

	@Override
	public <S extends Store> S save(S entity) {
		if (entity.get_id() != null) {
			Optional<Store> opt = findById(entity.get_id());
			if (opt.isPresent()) {
				if (StringUtils.isEmpty(entity.getAvatar())) {
					entity.setAvatar(opt.get().getAvatar());
				} else {
					entity.setAvatar(entity.getAvatar());
				}
				if (StringUtils.isEmpty(entity.getCover())) {
					entity.setCover(opt.get().getCover());
				} else {
					entity.setCover(entity.getCover());
				}
				if (StringUtils.isEmpty(entity.getFeaturedImages())) {
					entity.setFeaturedImages(opt.get().getFeaturedImages());
				} else {
					entity.setFeaturedImages(entity.getFeaturedImages());
				}
				if (entity.getOwnerId() == null) {
					entity.setOwnerId(opt.get().getOwnerId());
				}
				if(entity.getCreatedAt() == null) {
					entity.setCreatedAt(opt.get().getCreatedAt());
				}
			}
		}

		return storeRepo.save(entity);
	}

	@Override
	public List<Store> findAll() {
		return storeRepo.findAll();
	}

	@Override
	public Page<Store> findAll(Pageable pageable) {
		return storeRepo.findAll(pageable);
	}

	@Override
	public List<Store> findAll(Sort sort) {
		return storeRepo.findAll(sort);
	}

	@Override
	public Optional<Store> findById(Long id) {
		return storeRepo.findById(id);
	}

	@Override
	public long count() {
		return storeRepo.count();
	}

	@Override
	public void deleteById(Long id) {
		storeRepo.deleteById(id);
	}

	@Override
	public void delete(Store entity) {
		storeRepo.delete(entity);
	}

	@Override
	public Store findByOwnerId(Long ownerId) {
		return storeRepo.findByOwnerId(ownerId);
	}

	@Override
	public void deleteAll() {
		storeRepo.deleteAll();
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updateStore(Store entity) throws Exception {
		/*
		 * if (entity.get_id() != null) { Optional<Store> opt =
		 * findById(entity.get_id()); if (opt.isPresent()) { if
		 * (StringUtils.isEmpty(entity.getAvatar())) {
		 * entity.setAvatar(opt.get().getAvatar()); } else {
		 * entity.setAvatar(entity.getAvatar()); } if
		 * (StringUtils.isEmpty(entity.getCover())) {
		 * entity.setCover(opt.get().getCover()); } else {
		 * entity.setCover(entity.getCover()); } if
		 * (StringUtils.isEmpty(entity.getFeaturedImages())) {
		 * entity.setFeaturedImages(opt.get().getFeaturedImages()); } else {
		 * entity.setFeaturedImages(entity.getFeaturedImages()); } } }
		 */
		storeRepo.updateStore(entity.getBio(), entity.getAvatar(), entity.getCover(), entity.getFeaturedImages(),
				entity.get_id());
	}

	@Override
	public Page<Store> page(int index, int size) {
		// TODO Auto-generated method stub
		return storeRepo.findAll(PageRequest.of(index, size));
	}

}
