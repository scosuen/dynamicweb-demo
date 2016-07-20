package com.ying.dynamic_web_demo.dao;

import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernateDAO {
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	@PreDestroy
	public void closeSessionFactory() {
		try {
			sessionFactory.close();
		} catch (Exception e) {
		}
	}
}
