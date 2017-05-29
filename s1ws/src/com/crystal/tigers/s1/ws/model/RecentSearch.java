package com.crystal.tigers.s1.ws.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_recent_search database table.
 * 
 */
@Entity
@Table(name="user_recent_search")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "recentId")

public class RecentSearch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recent_id")
	private int recentId;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@Column(name="search_string")
	private String searchString;

	@Column(name="date_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	public RecentSearch() {
	}

	public int getRecentId() {
		return recentId;
	}

	public void setRecentId(int recentId) {
		this.recentId = recentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}