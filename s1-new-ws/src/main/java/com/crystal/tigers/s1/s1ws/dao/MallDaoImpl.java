package com.crystal.tigers.s1.s1ws.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import com.crystal.tigers.s1.s1ws.dbmodels.Mall;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mallDao")
public class MallDaoImpl extends AbstractDao<Integer, Mall> implements IMallDao {
	@PersistenceContext
	EntityManager em;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public List<Mall> searchMall(Mall mall) {
		Criteria criteria = session.createCriteria(Mall.class);
		Criterion mallName = Restrictions.like("mallName", mall.getMallName());
		Criterion mallLocation = Restrictions.like("mallLocation", mall.getMallLocation());
		Criterion completedCondition = Restrictions.disjunction().add(mallName).add(mallLocation);
		criteria.add(completedCondition);
		return (List<Mall>) criteria.list();
	}

	@Override
	public void createMall(Mall mall) {
		persist(mall);
	}

	@Override
	public void updateMall(Mall mall) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMall(int mall_id) {
		Mall mall = em.find(Mall.class, mall_id);
		em.getTransaction().begin();
		em.remove(mall);
		em.getTransaction().commit();

	}

	@Override
	public List<Mall> getMalls() {
		session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		tx = session.beginTransaction();
		List mallList = session.createCriteria(Mall.class).list();
		tx.commit();
		session.close();
		return mallList;
	}

	@Override
	public Mall getMallByID(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

}
