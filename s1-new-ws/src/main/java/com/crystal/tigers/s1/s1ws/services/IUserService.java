package com.crystal.tigers.s1.s1ws.services;

import com.crystal.tigers.s1.s1ws.dbmodels.User;

import java.util.List;

public interface IUserService {

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
