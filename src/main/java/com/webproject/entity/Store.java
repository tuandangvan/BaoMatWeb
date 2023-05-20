package com.webproject.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
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
@Table(name = "Stores")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;

	@Column(unique = true, nullable = false)
	@Size(max = 100)
	@Nationalized 
	private String name;

	@Column(nullable = false)
	@Size(max = 1000)
	@Nationalized 
	private String bio;

	private String slug;

	@OneToOne
	@JoinColumn(name = "ownerId", referencedColumnName = "_id")
	private User ownerId;

	@OneToMany(mappedBy = "_id")
	private List<User> staffIds;

	//@Column(columnDefinition = "boolean default false")
	private Boolean isActive;

	//@Column(columnDefinition = "boolean default false")
	private Boolean isOpen;

	private String avatar;
	private String cover;
	private String[] featuredImages;

	@OneToOne
	@JoinColumn(name = "commissionId", referencedColumnName = "_id")
	private Commission commissionId;
	private int point;

	//@Column(columnDefinition = "int default 3")
	private int[] rating;

	//@Column(columnDefinition = "double default 0")
	private double eWallet;

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

	/*
	 * public Store() { super(); // TODO Auto-generated constructor stub }
	 * 
	 * public Store(Long _id, @Size(max = 100) String name, @Size(max = 1000) String
	 * bio, String slug, User ownerId, List<User> staffIds, Boolean isActive,
	 * Boolean isOpen, String avatar, String cover, String[] featuredImages,
	 * Commission commissionId, int point, int[] rating, double eWallet, Date
	 * createdAt, Date updatedAt) { super(); this._id = _id; this.name = name;
	 * this.bio = bio; this.slug = slug; this.ownerId = ownerId; this.staffIds =
	 * staffIds; this.isActive = isActive; this.isOpen = isOpen; this.avatar =
	 * avatar; this.cover = cover; this.featuredImages = featuredImages;
	 * this.commissionId = commissionId; this.point = point; this.rating = rating;
	 * this.eWallet = eWallet; this.createdAt = createdAt; this.updatedAt =
	 * updatedAt; }
	 * 
	 * public Long get_id() { return _id; }
	 * 
	 * public void set_id(Long _id) { this._id = _id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getBio() { return bio; }
	 * 
	 * public void setBio(String bio) { this.bio = bio; }
	 * 
	 * public String getSlug() { return slug; }
	 * 
	 * public void setSlug(String slug) { this.slug = slug; }
	 * 
	 * public User getOwnerId() { return ownerId; }
	 * 
	 * public void setOwnerId(User ownerId) { this.ownerId = ownerId; }
	 * 
	 * public List<User> getStaffIds() { return staffIds; }
	 * 
	 * public void setStaffIds(List<User> staffIds) { this.staffIds = staffIds; }
	 * 
	 * public Boolean getIsActive() { return isActive; }
	 * 
	 * public void setIsActive(Boolean isActive) { this.isActive = isActive; }
	 * 
	 * public Boolean getIsOpen() { return isOpen; }
	 * 
	 * public void setIsOpen(Boolean isOpen) { this.isOpen = isOpen; }
	 * 
	 * public String getAvatar() { return avatar; }
	 * 
	 * public void setAvatar(String avatar) { this.avatar = avatar; }
	 * 
	 * public String getCover() { return cover; }
	 * 
	 * public void setCover(String cover) { this.cover = cover; }
	 * 
	 * public String[] getFeaturedImages() { return featuredImages; }
	 * 
	 * public void setFeaturedImages(String[] featuredImages) { this.featuredImages
	 * = featuredImages; }
	 * 
	 * public Commission getCommissionId() { return commissionId; }
	 * 
	 * public void setCommissionId(Commission commissionId) { this.commissionId =
	 * commissionId; }
	 * 
	 * public int getPoint() { return point; }
	 * 
	 * public void setPoint(int point) { this.point = point; }
	 * 
	 * public int[] getRating() { return rating; }
	 * 
	 * public void setRating(int[] rating) { this.rating = rating; }
	 * 
	 * public double geteWallet() { return eWallet; }
	 * 
	 * public void seteWallet(double eWallet) { this.eWallet = eWallet; }
	 * 
	 * public Date getCreatedAt() { return createdAt; }
	 * 
	 * public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
	 * 
	 * public Date getUpdatedAt() { return updatedAt; }
	 * 
	 * public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
	 */
}
