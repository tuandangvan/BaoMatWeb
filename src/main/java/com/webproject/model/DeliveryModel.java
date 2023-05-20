package com.webproject.model;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor
public class DeliveryModel {

	private Long _id;

	private String name;

	private String description;

	private int price;

	private Boolean isDeleted;
	
	private Date createdAt;
	private Date updatedAt;
	
	//
	private Boolean isEdit=false;

}
