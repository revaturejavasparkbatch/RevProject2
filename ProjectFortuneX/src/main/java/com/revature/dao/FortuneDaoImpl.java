package com.revature.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Fortune;
import com.revature.util.HibernateUtil;

public class FortuneDaoImpl implements FortuneDao{

	@Override
	public List<Fortune> getAllFortunesByUser(Integer uId) {
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM FORTUNE WHERE U_ID = :userId";
		SQLQuery query = s.createSQLQuery(sql);
		query.addEntity(Fortune.class);
		query.setParameter("userId", uId);
		
		List<Fortune> fortunes = query.list();
		s.close();
		return fortunes;
	}

	@Override
	public boolean createFortune(Fortune fortune) {
		int id = 0;
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		id = (int) s.save(fortune);
		tx.commit();
		s.close();
		
		if(0 != id)
			return true;
		
		return false;
	}
	
	@Override
	public void deleteFortune(String fId, Integer uId) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		String sql = "DELETE FROM FORTUNE WHERE F_ID = :fortuneId AND U_ID = :userId";
		SQLQuery query = s.createSQLQuery(sql);
		query.addEntity(Fortune.class);
		query.setParameter("fortuneId", fId);
		query.setParameter("userId", uId);
		
		query.executeUpdate();
		tx.commit();
		
		s.close();
	}

	@Override
	public void deleteAllFortunes(Integer uId) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		String sql = "DELETE FROM FORTUNE WHERE U_ID = :userId";
		SQLQuery query = s.createSQLQuery(sql);
		query.addEntity(Fortune.class);
		query.setParameter("userId", uId);
		
		query.executeUpdate();
		tx.commit();
		
		s.close();		
	}

}
