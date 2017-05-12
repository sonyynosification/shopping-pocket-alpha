package com.crystaltiger.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
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

	// bi-directional many-to-one association to Comment
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "store")
	@JsonManagedReference(value = "store-comment")

	private Set<Comment> comments;

	// bi-directional many-to-many association to Category
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "category_in_store", joinColumns = { @JoinColumn(name = "strore_id") }, inverseJoinColumns = {
			@JoinColumn(name = "cat_id") })
	private Set<Category> categories;

	// bi-directional many-to-many association to Mall
	@ManyToMany
	@JoinTable(name = "mall_has_store", joinColumns = { @JoinColumn(name = "strore_id") }, inverseJoinColumns = {
			@JoinColumn(name = "mall_id") })
	private Set<Mall> malls;

	// bi-directional many-to-many association to Brand
	@ManyToMany
	@JoinTable(name = "store_in_brand", joinColumns = { @JoinColumn(name = "strore_id") }, inverseJoinColumns = {
			@JoinColumn(name = "brand_id") })
	private Set<Brand> brands;

	public Store() {
	}

	public int getStroreId() {
		return this.storeId;
	}

	public void setStroreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStroreAddress() {
		return this.storeAddress;
	}

	public void setStroreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStroreEmail() {
		return this.storeEmail;
	}

	public void setStroreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}

	public String getStroreName() {
		return this.storeName;
	}

	public void setStroreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStrorePhone() {
		return this.storePhone;
	}

	public void setStrorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
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

	public Set<Mall> getMalls() {
		return this.malls;
	}

	public void setMalls(Set<Mall> malls) {
		this.malls = malls;
	}

	public Set<Brand> getBrands() {
		return this.brands;
	}

	public void setBrands(Set<Brand> brands) {
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