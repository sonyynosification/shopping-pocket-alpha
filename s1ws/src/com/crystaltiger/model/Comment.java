package com.crystaltiger.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommentPK id;

	@Column(name="user_comment")
	private String userComment;

	//bi-directional many-to-one association to Store
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name="Store_str_id")
	@JsonBackReference(value="store-comment")
	private Store store;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="User_usr_id")
	@JsonBackReference(value="user-comment")
	private User user;

	public Comment() {
	}

	public CommentPK getId() {
		return this.id;
	}

	public void setId(CommentPK id) {
		this.id = id;
	}

	public String getUserComment() {
		return this.userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}