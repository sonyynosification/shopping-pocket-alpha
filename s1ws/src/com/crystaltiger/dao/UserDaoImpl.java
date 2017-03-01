package com.crystaltiger.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crystaltiger.model.User;
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	 @Autowired
	 SessionFactory sessionFactory;

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
		Session session ;
	    User user ;

	    session = sessionFactory.getCurrentSession();
	    user = (User)session.load(User.class,user_id);
	    session.delete(user);

	    //This makes the pending delete to be done
	    session.flush() ;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		session = sessionFactory.openSession();
		  tx = session.beginTransaction();
		  List userList = session.createCriteria(User.class)
		    .list();
		  tx.commit();
		  session.close();
		  return userList;
	}

	@Override
	public User findUserByName(String userName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("user_name", userName));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void update(User user) {
		Session sesison = sessionFactory.openSession();
	
		 try {
		     tx = sesison.beginTransaction();
		     sesison.update(user);
		     tx.commit();
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
		 finally {
		     session.close();
		 }
		
	}

}
