package com.crystal.tigers.s1.ws.dao;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.model.RecentSearch;
import com.crystal.tigers.s1.ws.model.User;

import java.util.List;

/**
 * Created by sonyynoss on 5/30/17.
 */
public interface RecentSearchDAO {

    void deleteRecentSearch(RecentSearch recentSearch);

    int deleteAllRecentSearches(User user);

    public List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxReturn, SearchOrdering searchOrdering);

    void saveRecentSearch(RecentSearch recentSearch);
}
