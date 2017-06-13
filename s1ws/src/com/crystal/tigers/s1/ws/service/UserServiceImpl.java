package com.crystal.tigers.s1.ws.service;

import java.util.Date;
import java.util.List;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.common.utils.constants.SearchQualifiers;
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
	public void createRecentSearch(RecentSearch recentSearch) {
		if (null==(recentSearch.getDateCreated())) {
			recentSearch.setDateCreated(new Date());
		}
		saveRecentSearch(recentSearch);
	}

	@Override
	public void saveRecentSearch(RecentSearch recentSearch) {
		recentSearchDao.saveRecentSearch(recentSearch);
	}

	@Override
	public List<RecentSearch> findRecentSearches(User user) {
		RecentSearch recentSearch = new RecentSearch();
		recentSearch.setUser(user);
		return findRecentSearches(recentSearch);
	}

	@Override
	public List<RecentSearch> findRecentSearches(RecentSearch recentSearch) {
		int maxResults = SearchQualifiers.SEARCH_RANGE_UNLIMITED;
		return findRecentSearches(recentSearch, maxResults);
	}

	@Override
	public List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxResults) {
		SearchOrdering searchOrdering = SearchOrdering.newInstance();
		return findRecentSearches(recentSearch, maxResults, searchOrdering);
	}

	@Override
	public List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxResults, SearchOrdering searchOrdering) {
		List<RecentSearch> retVal = recentSearchDao.findRecentSearches(recentSearch, maxResults, searchOrdering);
		return retVal;
	}

	@Override
	public int deleteRecentSearches(User user) {
		int deletedCount = recentSearchDao.deleteAllRecentSearches(user);
		return deletedCount;
	}

	@Override
	public void deleteRecentSearches(RecentSearch recentSearch) {
		recentSearchDao.deleteRecentSearch(recentSearch);
	}


}
