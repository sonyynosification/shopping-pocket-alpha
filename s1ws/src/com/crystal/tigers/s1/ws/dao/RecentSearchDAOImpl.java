package com.crystal.tigers.s1.ws.dao;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.model.RecentSearch;
import com.crystal.tigers.s1.ws.model.Store;
import com.crystal.tigers.s1.ws.model.User;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonyynoss on 5/30/17.
 */
@Repository(value = "recentSearchDao")
public class RecentSearchDAOImpl extends AbstractDao<Integer,RecentSearch> implements RecentSearchDAO {
    EntityManager em;

    @Autowired
    SessionFactory sessionFactory;

    Session session = null;
    Transaction tx = null;

    @Override
    public void deleteRecentSearch(RecentSearch recentSearch) {
        em.getTransaction().begin();
        em.remove(recentSearch);
        em.getTransaction().commit();
    }

    public int deleteAllRecentSearches(User user) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("DELETE from user_recent_search where user_id = :p");

        Query query = em.createQuery(queryString.toString());
        int deletedCount = query.setParameter("p",user.getUserId()).executeUpdate();
        return deletedCount;
    }

    @Override
    public List<RecentSearch> findRecentSearches(RecentSearch recentSearch, int maxReturn, SearchOrdering searchOrdering) {
        session = sessionFactory.openSession();
        tx = null;
        tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(RecentSearch.class);
        //TODO: More Criterion to be created
        List<Criterion> criterionList = getRecentSearchCriterions(recentSearch);
        Disjunction disjunction = Restrictions.disjunction();
        Junction junction = null;
        Criterion completedCondition;
        for (Criterion c: criterionList
                ) {
            junction = disjunction.add(c);
        }
        completedCondition = junction;

        if (completedCondition != null) {
            criteria.add(completedCondition);
        }
        criteria.setMaxResults(maxReturn);

        setCriteriaOrders(criteria, searchOrdering);

        List<RecentSearch> results = criteria.list();
        tx.commit();
        session.close();
        return results;
    }

    protected List<Criterion> getRecentSearchCriterions(RecentSearch recentSearch) {
        List<AbstractMap.SimpleEntry<String, Object>> objectMap = getRecentSearchObjectMapping(recentSearch);
        List<Criterion> retList = new ArrayList<>();

        for (AbstractMap.SimpleEntry<String,Object> obj : objectMap) {
            //TODO: nullable check here should be for other types than String
            if (obj.getValue() != null) {
                Criterion criterion = Restrictions.like(obj.getKey(), obj.getValue());
                retList.add(criterion);
            }
        }
        return retList;
    }

    protected List<AbstractMap.SimpleEntry<String,Object>> getRecentSearchObjectMapping(RecentSearch recentSearch) {
        List<AbstractMap.SimpleEntry<String,Object>> retList = new ArrayList<>();
        AbstractMap.SimpleEntry<String, Object> recentSearchNameMapping =
                new AbstractMap.SimpleEntry<String, Object>("searchString", recentSearch.getSearchString());
        retList.add(recentSearchNameMapping);

        return retList;
    }



    @Override
    public void saveRecentSearch(RecentSearch recentSearch) {
        persist(recentSearch);
    }
}
