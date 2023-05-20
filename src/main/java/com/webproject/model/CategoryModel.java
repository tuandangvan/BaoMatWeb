package com.webproject.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;


@Data

@AllArgsConstructor

@NoArgsConstructor
public class CategoryModel {
	private long _id;
	@NotEmpty

	private String name;
	private String slug;
	private String image;
	private Boolean isDeleted;
	private Date createdAt;
	private Date updatedAt;
	private MultipartFile imageFile;
	private Boolean isEdit=false;
}
