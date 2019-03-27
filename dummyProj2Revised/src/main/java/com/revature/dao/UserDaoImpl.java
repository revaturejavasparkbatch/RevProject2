package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao{

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
		User u = (User) s.get(User.class, email);
		s.close();
		return u;
	}

	@Override
	public boolean createUser(User newUser) {
		int id = 0;
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		id = (int) s.save(newUser);
		tx.commit();
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
		User user = (User) s.get(User.class, change.getEmail());
		
		//update user with any non-null fields from change
		if(change.getEmail() != null)
			user.setEmail(change.getEmail());
		
		if(change.getfName() != null)
			user.setfName(change.getfName());
		
		if(change.getlName() != null)
			user.setlName(change.getlName());
		
		if(change.getPassword() != null)
			user.setPassword(change.getPassword());
		
		tx.commit();
		s.close();
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
