package com.webproject.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	//Từ entity
	private Long _id;
	private String firstName;
	private String lastName;
	private String slug;
	private String idCard;
	private String email;
	private String phone;
	private boolean isEmailActive;
	private boolean isPhoneActive;
	private String salt;
	private String hashedPassword;
	private String roles;
	private String address;
	private String avatar;
	private String cover;
	private Integer point;
	private Double eWallet;
	private Date createdAt;
	private Date updatedAt;
	
	//Thêm 
	private String password;
	private String password2;
	private String currentpassword;
	
}
