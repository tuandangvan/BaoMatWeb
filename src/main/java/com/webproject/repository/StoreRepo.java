package com.webproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {
	@Query(value = "Select * from Stores where ownerId = ?", nativeQuery = true)
	Store findByOwnerId(Long ownerId);

	@Modifying(clearAutomatically = true)
	@Query(value = "Update Stores set bio = ?1, avatar=?2, cover=?3, featuredImages=?4 where _id = ?5", nativeQuery = true)
	void updateStore(String bio, String avatar, String cover, String[] featuredImages, Long id) throws Exception;
}
