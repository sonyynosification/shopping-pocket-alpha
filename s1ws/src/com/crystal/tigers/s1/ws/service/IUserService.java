package com.crystal.tigers.s1.ws.service;

import java.util.List;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.model.RecentSearch;
import com.crystal.tigers.s1.ws.model.User;

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

	//==== RECENT SEARCHES ====

    List<RecentSearch> getUserRecentSearches(User user);

	boolean exists(RecentSearch recentSearch);

	void saveRecentSearch(RecentSearch recentSearch);

	List<RecentSearch> findRecentSearches(User user);

	List<RecentSearch> findRecentSearches(RecentSearch recentSearch);

	List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxResults);

	//List<RecentSearch> findRecentSearches(User user, int maxResults, SearchOrdering searchOrdering);

	List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxResults, SearchOrdering searchOrdering);

	/**
	 * Delete all RecentSearch of user
	 * @param user
	 */
	void deleteRecentSearches(User user);

	/**
	 * Delete some specific RecentSearch
	 * @param recentSearch
	 */
	void deleteRecentSearches(User user, RecentSearch recentSearch);

}
