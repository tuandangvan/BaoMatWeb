package com.webproject.model;

import java.util.Date;

import com.webproject.entity.Commission;
import com.webproject.entity.Delivery;
import com.webproject.entity.Store;
import com.webproject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
	private Long _id;
	private User userId;
	private Store storeId;
	private Delivery deliveryId;
	private Commission commissionId;
	private String address;
	private String phone;
	private String status;
	private Boolean isPaidBefore;
	private double amountFromUser;
	private double amountFromStore;
	private double amountToStore;
	private double amountToGD;
	private Date createdAt;
	private Date updatedAt;
	
	private Long delivery;
}
