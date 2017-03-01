package com.crystaltiger.dao;

import java.util.List;

import com.crystaltiger.model.User;

public interface UserDao {	
	void saveUser(User user);	
	void update(User user);
	void delete(int user_id);	
	List<User> findAllUsers();
	User findUserByName(String userName);
	User findById(int id);
	
}
