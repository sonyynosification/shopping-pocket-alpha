package com.crystal.tigers.s1.ws.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the comment database table.
 * 
 */
@Embeddable
public class CommentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int store_str_id;

	@Column(insertable=false, updatable=false)
	private int user_usr_id;

	public CommentPK() {
	}
	public int getStore_str_id() {
		return this.store_str_id;
	}
	public void setStore_str_id(int store_str_id) {
		this.store_str_id = store_str_id;
	}
	public int getUser_usr_id() {
		return this.user_usr_id;
	}
	public void setUser_usr_id(int user_usr_id) {
		this.user_usr_id = user_usr_id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CommentPK)) {
			return false;
		}
		CommentPK castOther = (CommentPK)other;
		return 
			(this.store_str_id == castOther.store_str_id)
			&& (this.user_usr_id == castOther.user_usr_id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.store_str_id;
		hash = hash * prime + this.user_usr_id;
		
		return hash;
	}
}