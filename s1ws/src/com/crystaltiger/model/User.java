package com.crystaltiger.model;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;

	@Size(min=3, max=50)
	@Column(name = "user_name", nullable = false)
	private String user_name;
	
	@Column(name = "user_password", nullable = false)
	private String user_password;
	
	@Column(name = "user_location")
	private String user_location;
	
	@Column(name = "user_recent_search")
	private String user_recent_search;
	
	@Column(name = "user_favorite")
	private String user_favorite;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_location() {
		return user_location;
	}

	public void setUser_location(String user_location) {
		this.user_location = user_location;
	}

	public String getUser_recent_search() {
		return user_recent_search;
	}

	public void setUser_recent_search(String user_recent_search) {
		this.user_recent_search = user_recent_search;
	}

	public String getUser_favorite() {
		return user_favorite;
	}

	public void setUser_favorite(String user_favorite) {
		this.user_favorite = user_favorite;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_password=" + user_password
				+ ", user_location=" + user_location + ", user_recent_search=" + user_recent_search + ", user_favorite="
				+ user_favorite + "]";
	}
   
	
}