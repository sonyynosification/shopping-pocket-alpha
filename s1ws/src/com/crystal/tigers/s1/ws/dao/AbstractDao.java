package com.crystal.tigers.s1.ws.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.AbstractMap;
import java.util.Map;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}

	protected Criteria setCriteriaOrders(Criteria criteria, SearchOrdering orders) {
		for (AbstractMap.SimpleEntry<String, Integer> rule : orders.getSearchOrdersRules()) {
		    int order = rule.getValue();
		    String field = rule.getKey();
		    if (SearchOrdering.SEARCH_ORDER_ASCENDANT == order) {
		        criteria.addOrder(Order.asc(field));
            }
            else if (SearchOrdering.SEARCH_ORDER_DESCENDANT == order) {
		        criteria.addOrder(Order.desc(field));
            }
        }
        return criteria;
	}

}
