package com.webproject.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Orders")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "storeId")
	private Store storeId;
	
	@OneToOne
	@JoinColumn(name = "deliveryId", referencedColumnName = "_id")
	private Delivery deliveryId;
	
	@ManyToOne
	@JoinColumn(name =  "commissionId")
	private Commission commissionId;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String phone;
	
	//@Column(nullable = false, columnDefinition = "nvarchar(200) default 'not precessed'")
	@Nationalized
	private String status;
	
	//@Column(columnDefinition = "boolean default false")
	private Boolean isPaidBefore;
	
	@Column(nullable = false)
	@Min(value = 0)
	private double amountFromUser;
	
	@Column(nullable = false)
	@Min(value = 0)
	private double amountFromStore;
	
	@Column(nullable = false)
	@Min(value = 0)
	private double amountToStore;
	
	@Column(nullable = false)
	@Min(value = 0)
	private double amountToGD;
	
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
