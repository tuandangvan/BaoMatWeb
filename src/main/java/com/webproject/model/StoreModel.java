package com.webproject.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StoreModel {
	private Long _id;
	private String name;
	@Column(nullable = false)
	@Size(max = 1000)
	private String bio;
	@Column(unique = true)
	private String slug;
	private Long ownerId;
	private List<Long> staffIds;
	private Boolean isActive;
	private Boolean isOpen;
	private String avatar;
	private String cover;
	private String[] featuredImages;
	private MultipartFile avatarFile;
	private MultipartFile coverFile;
	private MultipartFile[] featuredImagesFile;
	private Long commissionId;
	private int point;
	private int[] rating;
	private double eWallet;
	private Date createdAt;
	private Date updatedAt;

}
