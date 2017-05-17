package com.crystal.tigers.s1.ws.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@Table(name = "store")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "storeId")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private int storeId;

	@Column(name = "store_address")
	private String storeAddress;

	@Column(name = "store_email")
	private String storeEmail;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "store_phone")
	private String storePhone;

	@Column(name = "store_geolat")
	private String storeGeoLat;

	@Column(name = "store_geolng")
	private String storeGeoLng;

	@Column(name = "store_image")
	private String storeImage;

	@Column(name = "store_code")
	private String storeCode;

	@Column(name = "store_parent_id")
	private String storePid;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.store", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 10)
	@JsonIgnore
	private List<Comment> comments;
	
	
	// bi-directional many-to-many association to Category
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "category_in_store", joinColumns = { @JoinColumn(name = "store_id") }, inverseJoinColumns = {
			@JoinColumn(name = "cat_id") })
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 10)
	//@JsonIgnore
	private List<Category> categories;
	

	// bi-directional many-to-many association to Mall
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mall_has_store", joinColumns = { @JoinColumn(name = "store_id") }, inverseJoinColumns = {
			@JoinColumn(name = "mall_id") })
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 10)
	//@JsonIgnore
	private List<Mall> malls;

	// bi-directional many-to-many association to Brand
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "store_in_brand", joinColumns = { @JoinColumn(name = "store_id") }, inverseJoinColumns = {
			@JoinColumn(name = "brand_id") })
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 10)
	//@JsonIgnore
	private List<Brand> brands;

	public Store() {
	}

	public int getStoreId() {
		return this.storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreAddress() {
		return this.storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreEmail() {
		return this.storeEmail;
	}

	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStorePhone() {
		return this.storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStorePid() {
		return storePid;
	}

	public void setStorePid(String storePid) {
		this.storePid = storePid;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setStore(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setStore(null);

		return comment;
	}

	public List<Mall> getMalls() {
		return this.malls;
	}

	public void setMalls(List<Mall> malls) {
		this.malls = malls;
	}

	public List<Brand> getBrands() {
		return this.brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public String getStoreImage() {
		return storeImage;
	}

	public void setStoreImage(String storeImage) {
		this.storeImage = storeImage;
	}

	public String getStoreGeoLat() {
		return storeGeoLat;
	}

	public void setStoreGeoLat(String storeGeoLat) {
		this.storeGeoLat = storeGeoLat;
	}

	public String getStoreGeoLng() {
		return storeGeoLng;
	}

	public void setStoreGeoLng(String storeGeoLng) {
		this.storeGeoLng = storeGeoLng;
	}

}