package com.crystal.tigers.s1.s1ws.dbmodels;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Column(name="user_favorite")
	private String userFavorite;

	@Column(name="user_location")
	private String userLocation;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_password")
	private String userPassword;

	@Column(name="user_recent_search")
	private String userRecentSearch;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user",cascade = CascadeType.ALL)	
	@Fetch(FetchMode.SELECT)
        @BatchSize(size = 10)
	@JsonIgnore	
	private List<Comment> comments;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFavorite() {
		return this.userFavorite;
	}

	public void setUserFavorite(String userFavorite) {
		this.userFavorite = userFavorite;
	}

	public String getUserLocation() {
		return this.userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRecentSearch() {
		return this.userRecentSearch;
	}

	public void setUserRecentSearch(String userRecentSearch) {
		this.userRecentSearch = userRecentSearch;
	}

	
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((userFavorite == null) ? 0 : userFavorite.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userLocation == null) ? 0 : userLocation.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result + ((userRecentSearch == null) ? 0 : userRecentSearch.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (userFavorite == null) {
			if (other.userFavorite != null)
				return false;
		} else if (!userFavorite.equals(other.userFavorite))
			return false;
		if (userId != other.userId)
			return false;
		if (userLocation == null) {
			if (other.userLocation != null)
				return false;
		} else if (!userLocation.equals(other.userLocation))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userRecentSearch == null) {
			if (other.userRecentSearch != null)
				return false;
		} else if (!userRecentSearch.equals(other.userRecentSearch))
			return false;
		return true;
	}

	/*public com.crystal.tigers.s1.s1ws.dbmodels.Comment addComment(com.crystal.tigers.s1.s1ws.dbmodels.Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public com.crystal.tigers.s1.s1ws.dbmodels.Comment removeComment(com.crystal.tigers.s1.s1ws.dbmodels.Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}*/
	
	

}