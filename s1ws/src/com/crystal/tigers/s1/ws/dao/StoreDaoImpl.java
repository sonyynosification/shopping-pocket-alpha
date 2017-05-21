package com.crystal.tigers.s1.ws.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
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
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public List<Store> findStores(Store searchStore, int maxReturn, SearchOrdering ordering) {
        session = sessionFactory.openSession();
        tx = null;
        tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Store.class);
            //TODO: More Criterion to be created
            Criterion storeName = Restrictions.like("storeName", searchStore.getStoreName());
            Criterion storeLocation = Restrictions.like("storeAddress", searchStore.getStoreAddress());
            Criterion completedCondition = Restrictions.disjunction().add(storeName).add(storeLocation);

            criteria.add(completedCondition);

            criteria.setMaxResults(maxReturn);

            setCriteriaOrders(criteria, ordering);

            List<Store> results = criteria.list();
        tx.commit();
        session.close();
        return results;
	}

}
