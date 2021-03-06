package com.crystal.tigers.s1.s1ws.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.crystal.tigers.s1.s1ws.dbmodels.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	@PersistenceContext
	EntityManager em;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	 Session session = null;
	 Transaction tx = null;

	@Override
	public User findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveUser(User user) {
		persist(user);

	}

	@Override
	public void delete(int user_id) {
		User user = em.find(User.class, user_id);
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();

	}

	@Override
	public List<User> findAllUsers() {
		/*session = sessionFactory.openSession();
		  tx = session.beginTransaction();
		  List userList = session.createCriteria(User.class)
		    .list();
		  tx.commit();
		  session.close();
		  return userList;*/
		session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		tx = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(User.class);
		Root contactRoot = criteria.from(User.class);
		criteria.select(contactRoot);
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public User findUserByName(String userName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userName", userName));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void update(User user) {
		System.out.println("This update function is not updated code yet. Please update code in UserDaoImpl");
	}

	@Override
	public List<User> findUserByModel(User user) {
		//Criteria criteria = createEntityCriteria();
		//criteria.add(Restrictions.like("userName", user.getUserName()).ignoreCase());		
		//criteria.add(Restrictions.like("userLocation", user.getUserLocation()).ignoreCase());
		//criteria.add(Restrictions.or(Restrictions.and(Restrictions.isNotNull("userName"), Restrictions.eq("userName", user.getUserName())), Restrictions.isNull("userName")));
		//criteria.add(Restrictions.or(Restrictions.and(Restrictions.isNotNull("userLocation"), Restrictions.eq("userLocation", user.getUserLocation())), Restrictions.isNull("userLocation")));
		Criteria criteria = session.createCriteria(User.class);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.eq("userName",user.getUserName()));
		or.add(Restrictions.eq("userLocation",user.getUserLocation()));		
		criteria.add(or);
		
		return (List<User>) criteria.list();
	}

}
