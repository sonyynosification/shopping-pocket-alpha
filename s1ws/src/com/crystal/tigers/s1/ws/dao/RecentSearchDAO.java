package com.crystal.tigers.s1.ws.dao;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.model.RecentSearch;

import java.util.List;

/**
 * Created by sonyynoss on 5/30/17.
 */
public interface RecentSearchDAO {
    void createRecentSearch(RecentSearch recentSearch);

    void deleteAllRecentSearches();

    void deleteRecentSearch(RecentSearch recentSearch);

    public List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxReturn, SearchOrdering searchOrdering);

    public RecentSearch getRecentSearchByID(int id);
}
