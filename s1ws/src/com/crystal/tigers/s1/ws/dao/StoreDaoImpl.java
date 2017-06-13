package com.crystal.tigers.s1.ws.dao;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
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

import com.crystal.tigers.s1.ws.model.Store;

@Repository("storeDao")
public class StoreDaoImpl extends AbstractDao<Integer, Store> implements StoreDao {
	EntityManager em;

	@Autowired
	SessionFactory sessionFactory;
	
	Session session = null;
	 Transaction tx = null;

	@Override
	public List<Store> searchStore(Store store) {

		Criteria criteria = session.createCriteria(Store.class);
		Criterion storeName = Restrictions.like("storeName", store.getStoreName());
		Criterion storeLocation = Restrictions.like("storeLocation", store.getStoreAddress());
		Criterion completedCondition = Restrictions.disjunction().add(storeName).add(storeLocation);
		criteria.add(completedCondition);
		return (List<Store>) criteria.list();
	}

	@Override
	public List<Store> getStores() {
		session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List storeList = session.createCriteria(Store.class)
            .list();
        tx.commit();
        session.close();
        return storeList;
	}

	@Override
	public void createStore(Store store) {
		persist(store);		
	}

	@Override
	public void updateStore(Store store) {
			
	}

	@Override
	public void deleteStore(int store_id) {
		Store store = em.find(Store.class, store_id);
		em.getTransaction().begin();
		em.remove(store);
		em.getTransaction().commit();
		
	}

	@Override
	public Store getStoreByID(int id) {
		return getByKey(id);
	}

	@Override
	public List<Store> findStores(Store searchStore, int maxReturn, SearchOrdering ordering) {
        session = sessionFactory.openSession();
        tx = null;
        tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Store.class);
        //TODO: More Criterion to be created
        List<Criterion> criterionList = getStoreCriterions(searchStore);
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

        setCriteriaOrders(criteria, ordering);
        List<Store> results = criteria.list();
        tx.commit();
        session.close();
        return results;
	}

	protected List<Criterion> getStoreCriterions(Store store) {
        List<AbstractMap.SimpleEntry<String, Object>> objectMap = getStoreObjectMapping(store);
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

    protected List<AbstractMap.SimpleEntry<String,Object>> getStoreObjectMapping(Store store) {
        List<AbstractMap.SimpleEntry<String,Object>> retList = new ArrayList<>();
        AbstractMap.SimpleEntry<String, Object> storeNameMapping =
                new AbstractMap.SimpleEntry<String, Object>("storeName", store.getStoreName());
        AbstractMap.SimpleEntry<String, Object> storeEmailMapping =
                new AbstractMap.SimpleEntry<String, Object>("storeEmail", store.getStoreEmail());
        AbstractMap.SimpleEntry<String, Object> storeAddressMapping =
                new AbstractMap.SimpleEntry<String, Object>("storeAddress", store.getStoreAddress());

        retList.add(storeNameMapping);
        retList.add(storeEmailMapping);
        retList.add(storeAddressMapping);
        return retList;
    }

    //protected final List<AbstractMap.SimpleEntry<String,Object>> STORE_OBJECT_MAPPING =

}
