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
	public void deleteUserByName(String userName) {
		Query query = getSession().createQuery("delete from user where user_name = :userName");
		query.setString("user_name", userName);
		query.executeUpdate();
		
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

}
