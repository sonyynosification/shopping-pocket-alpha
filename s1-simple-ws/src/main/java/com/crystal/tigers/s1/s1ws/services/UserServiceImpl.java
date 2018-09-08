package com.crystal.tigers.s1.s1ws.services;

import java.util.List;

import com.crystal.tigers.s1.s1ws.dao.UserDao;
import com.crystal.tigers.s1.s1ws.dbmodels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;
	@Override
	public User findById(int id) {
		User retUser = userDao.findById(id);
		return retUser;
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
		
	}

	@Override
	public void updateUser(User user) {
		//TODO: implementation needed
		
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
		
	}

	@Override
	public List<User> findAllUsers() {
		List<User> allUsersList = userDao.findAllUsers();
		return allUsersList;
	}

	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(userName);
	}

	@Override
	public boolean isUserNameUnique(Integer id, String userName) {
		User user = userDao.findUserByName(userName);
		boolean isUnique =  ( user == null
                            || ((id != null) && (user.getUserId() == id)));
		return isUnique;
	}

	@Override
	public boolean exists(User user) {		
		boolean isExists = findUserByName(user.getUserName()) != null;
		return isExists;
	}

	@Override
	public List<User> searchByModel(User user) {
		List<User> users = userDao.findUserByModel(user);
		return users;
	}

   
}
