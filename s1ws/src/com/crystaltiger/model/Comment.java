package com.crystaltiger.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name = "comment")
@AssociationOverrides({ @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "pk.store", joinColumns = @JoinColumn(name = "store_id")) })
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private UserStoreId pk = new UserStoreId();
	

	@Column(name = "user_comment")
	private String userComment;

	public Comment() {
	}

	@EmbeddedId
	public UserStoreId getPk() {
		return pk;
	}

	public void setPk(UserStoreId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Store getStore() {
		return getPk().getStore();
	}

	public void setStore(Store store) {
		getPk().setStore(store);
	}

	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}
	
	public String getUserComment() {
		return this.userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((userComment == null) ? 0 : userComment.hashCode());
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
		Comment other = (Comment) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (userComment == null) {
			if (other.userComment != null)
				return false;
		} else if (!userComment.equals(other.userComment))
			return false;
		return true;
	}
	

}