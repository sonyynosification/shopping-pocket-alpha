package com.crystal.tigers.s1.ws.dao;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.model.RecentSearch;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by sonyynoss on 5/30/17.
 */
@Repository(value = "recentSearchDao")
public class RecentSearchDAOImpl implements RecentSearchDAO {
    @Override
    public void createRecentSearch(RecentSearch recentSearch) {
        //TODO: implementation needed
        throw new NotImplementedException();
    }

    @Override
    public void deleteAllRecentSearches() {
        //TODO: implementation needed
        throw new NotImplementedException();
    }

    @Override
    public void deleteRecentSearch(RecentSearch recentSearch) {
        //TODO: implementation needed
        throw new NotImplementedException();
    }

    @Override
    public List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxReturn, SearchOrdering searchOrdering) {
        //TODO: implementation needed
        throw new NotImplementedException();
    }

    @Override
    public RecentSearch getRecentSearchByID(int id) {
        //TODO: implementation needed
        throw new NotImplementedException();
    }
}
