package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory(String fileName) {
		if(sessionFactory == null || sessionFactory.isClosed()) {
			Configuration c = new Configuration().configure(fileName);
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
			
			sessionFactory = c.buildSessionFactory(sr);
		}
		
		return sessionFactory;
	}
	
	public static Session getSession() {
		return getSessionFactory("hibernate.cfg.xml").openSession();
	}
	
	public static void closeSessionFactory() {
		sessionFactory.close();
	}
}
