package com.crystal.tigers.s1.ws.dao;

import java.util.List;

import com.crystal.tigers.s1.ws.model.User;

public interface UserDao {	
	void saveUser(User user);	
	void update(User user);
	void delete(int user_id);	
	List<User> findAllUsers();
	User findUserByName(String userName);
	User findById(int id);
	List<User> findUserByModel(User user);
	
}
