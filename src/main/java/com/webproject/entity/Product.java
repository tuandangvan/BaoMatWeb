package com.webproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	
	@Column(nullable = false)
	@Size(max = 1000)
	@Nationalized
	private String name;
	
	private String slug;
	
	@Column(nullable = false)
	@Size(max = 1000)
	@Nationalized 
	private String description;
	
	@Column(nullable = false)
	@Min(value = 0)
	private double price;
	
	@Column(nullable = false)
	@Min(value = 0)
	private double promotionalPrice;
	
	@Column(nullable = false)
	@Min(value = 0)
	private int quantity;
	
	//@Column(columnDefinition = "int default 0")
	@Min(value = 0)
	private int sold;
	
	//@Column(columnDefinition = "boolean default true")
	private Boolean isActive;
	
	//@Column(columnDefinition = "boolean default true")
	private Boolean isSelling;
	
	private String[] listImages;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category categoryId;
	
	@OneToMany(mappedBy = "_id")
	private List<StyleValue> styleValueIds;
	
	@ManyToOne
	@JoinColumn(name = "storeId")
	private Store storeId;
	
	//@Column(columnDefinition = "int default 3")
	private int rating;
	
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
