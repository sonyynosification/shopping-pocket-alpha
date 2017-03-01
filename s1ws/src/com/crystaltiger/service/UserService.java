package com.crystaltiger.service;

import java.util.List;

import com.crystaltiger.model.User;

public interface UserService {

	User findById(int id);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void delete(int id);

	List<User> findAllUsers(); 
	
	User findUserByName(String userName);

	boolean isUserNameUnique(Integer id, String userName);
	boolean exists(User user);

	List<User> searchByModel(User user);
}
