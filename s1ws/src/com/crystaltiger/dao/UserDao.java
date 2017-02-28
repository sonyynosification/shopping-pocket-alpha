package com.crystaltiger.dao;

import java.util.List;

import com.crystaltiger.model.User;

public interface UserDao {
	User findById(int id);

	void saveUser(User user);
	
	void deleteUserByName(String userName);
	
	List<User> findAllUsers();

	User findUserByName(String userName);	
}
