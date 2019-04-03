package com.revature.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.bean.Fortune;
import com.revature.bean.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao{

	FortuneDao fd = new FortuneDaoImpl();
	
	@Override
	public List<User> getAllUsers() {
		Session s = HibernateUtil.getSession();
		List<User> users = s.createCriteria(User.class).list();
		s.close();
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM USERS WHERE U_EMAIL = :userEmail";
		SQLQuery query = s.createSQLQuery(sql);
		query.addEntity(User.class);
		query.setParameter("userEmail", email);
		
		User u = (User) query.uniqueResult();
		s.close();
		return u;
	}

	@Override
	public boolean createUser(User newUser) {
		int id = 0;
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		try {
			id = (int) s.save(newUser);
			tx.commit();
		}
		catch(ConstraintViolationException e) {
			return false;
		}
		s.close();
		
		if(0 != id)
			return true;
		
		return false;
	}

	@Override
	public void updateUser(User change) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		//get user from DB
		User user = (User) s.get(User.class, change.getId());
		
		//update user with any non-null fields from change
		if(!change.getEmail().equals(""))
			user.setEmail(change.getEmail());
		
		if(!change.getfName().equals(""))
			user.setfName(change.getfName());
		
		if(!change.getlName().equals(""))
			user.setlName(change.getlName());
		
		if(!change.getPassword().equals(""))
			user.setPassword(change.getPassword());
		
		tx.commit();
		s.close();
	}

	@Override
	public void deleteUser(User user) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		//find all fortunes related to user
		List<Fortune> fortunes = fd.getAllFortunesByUser(user.getId());
		//if any fortunes are found delete all user related fortunes
		if(fortunes.size() > 0)
			fd.deleteAllFortunes(user.getId());
		
		//find user from DB
		String sql = "DELETE FROM USERS WHERE U_EMAIL = :userEmail";
		SQLQuery query = s.createSQLQuery(sql);
		query.addEntity(User.class);
		query.setParameter("userEmail", user.getEmail());
		query.executeUpdate();
		
		tx.commit();
		
		s.close();
	}

}