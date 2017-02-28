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
@Table(name="USER")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;

	@Size(min=3, max=50)
	@Column(name = "USER_NAME", nullable = false)
	private String userName;
	
	@Column(name = "USER_PASSWORD", nullable = false)
	private String userPassword;
	
	@Column(name = "USER_LOCATION")
	private String userLocation;
	
	@Column(name = "USER_RECENT_SEARCH")
	private String userRecentSearch;
	
	@Column(name = "USER_FAVORITE")
	private String userFavorite;


    public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserLocation() {
		return userLocation;
	}



	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}


	public String getUserRecentSearch() {
		return userRecentSearch;
	}


	public void setUserRecentSearch(String userRecentSearch) {
		this.userRecentSearch = userRecentSearch;
	}


	public String getUserFavorite() {
		return userFavorite;
	}

	public void setUserFavorite(String userFavorite) {
		this.userFavorite = userFavorite;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userLocation=" + userLocation + ", userRecentSearch=" + userRecentSearch + ", userFavorite="
				+ userFavorite + "]";
	}


	
}