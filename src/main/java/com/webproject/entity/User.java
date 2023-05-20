package com.webproject.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Users")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;

	@Size(max = 32)
	@NotNull
	private String firstName;

	@Size(max = 32)
	@NotNull
	private String lastName;

	private String slug;

	@Column(unique = true)
	private String idCard;

	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String phone;

	//@Column(name = "isEmailActive",nullable = false, columnDefinition = "boolean default false")
	private boolean isEmailActive;	

	//@Column(name = "isPhoneActive",nullable = false,columnDefinition = "boolean default false")
	private boolean isPhoneActive;

	private String salt;

	@NotNull
	private String hashedPassword;

	//@Column(name = "roles",nullable = false,columnDefinition = "varchar(200) default 'User'")
	private String roles;

	private String address;
	private String avatar;
	private String cover;
	private Integer point;
	private Double eWallet;
	private Date createdAt;
	private Date updatedAt;

	@PrePersist
	void createdAt() {
		this.createdAt = this.updatedAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}
}
