package com.crystal.tigers.s1.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crystal.tigers.s1.ws.dao.UserDao;
import com.crystal.tigers.s1.ws.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao; 
	@Override
	public User findById(int id) {		
		return userDao.findById(id);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
		
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(userName);
	}

	@Override
	public boolean isUserNameUnique(Integer id, String userName) {
		User user = userDao.findUserByName(userName);
		return ( user == null || ((id != null) && (user.getUserId() == id)));
	}

	@Override
	public boolean exists(User user) {		
		return findUserByName(user.getUserName()) != null;
	}

	@Override
	public List<User> searchByModel(User user) {
		List<User> users = userDao.findUserByModel(user);
		return users;
	}

   
}
