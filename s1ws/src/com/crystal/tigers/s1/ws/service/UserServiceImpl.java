package com.crystal.tigers.s1.ws.service;

import java.util.List;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.dao.RecentSearchDAO;
import com.crystal.tigers.s1.ws.model.RecentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crystal.tigers.s1.ws.dao.UserDao;
import com.crystal.tigers.s1.ws.model.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RecentSearchDAO recentSearchDao;

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
		throw new NotImplementedException();
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

	@Override
	public List<RecentSearch> getUserRecentSearches(User user) {
		//TODO: implementation needed
		return null;
	}



	@Override
	public boolean exists(RecentSearch recentSearch) {
		List<RecentSearch> matchList = recentSearchDao.findRecentSearches(
				recentSearch,1, SearchOrdering.newInstance());
		return !matchList.isEmpty();
	}

	@Override
	public void saveRecentSearch(RecentSearch recentSearch) {
		//TODO: implementation needed
	}

	@Override
	public List<RecentSearch> findRecentSearches(User user) {
		//TODO: implementation needed
		throw new NotImplementedException();
	}

	@Override
	public List<RecentSearch> findRecentSearches(RecentSearch recentSearch) {
		//TODO: implementation needed
		throw new NotImplementedException();
	}

	@Override
	public List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxResults) {
		//TODO: implementation needed
		throw new NotImplementedException();
	}

	@Override
	public List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxResults, SearchOrdering searchOrdering) {
		//TODO: implementation needed
		throw new NotImplementedException();
	}

	@Override
	public void deleteRecentSearches(User user) {
		//TODO: implementation needed
		throw new NotImplementedException();
	}

	@Override
	public void deleteRecentSearches(User user, RecentSearch recentSearch) {
		//TODO: implementation needed
		throw new NotImplementedException();
	}


}
