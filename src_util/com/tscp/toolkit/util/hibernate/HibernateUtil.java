package com.tscp.toolkit.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static Configuration getInitializedConfiguration() {
		Configuration config = new Configuration();
		//config.configure("hibernate.tscpmvna.cfg.xml");
		config.addAnnotatedClass(com.tscp.toolkit.domain.user.User.class);
		config.addAnnotatedClass(com.tscp.toolkit.domain.authority.Authority.class);
		config.addAnnotatedClass(com.tscp.toolkit.domain.billing.Invoice.class);
		config.addAnnotatedClass(com.tscp.toolkit.domain.billing.InvoiceDetail.class);		
		return config;
	}

	public static Session getSession() {
		if (sessionFactory == null) {
			Configuration config = getInitializedConfiguration();
			sessionFactory = config.buildSessionFactory();
		}
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}
	
	/**
	* Used to close an open session and catch any exceptions during the process.
	*
	* @param session
	* @throws RuntimeException
	*/
	  public static void closeSession(Session session) throws RuntimeException {
	    if (session.isOpen()) {
	      try {
	        session.close();
	      } catch (HibernateException e) {
	        throw new RuntimeException("Error closing session. " + e.getMessage());
	      }
	    }
	  }

	  /**
	* Used to commit transactions and catch any exceptions during the process.
	*
	* @param transaction
	* @throws RuntimeException
	*/
	  public static void commitTransaction(Transaction transaction) throws RuntimeException {
	    if (transaction != null && transaction.isActive()) {
	      try {
	        transaction.commit();
	      } catch (HibernateException e) {
	        throw new RuntimeException("Error commiting back transaction. " + e.getMessage());
	      }
	    }
	  }

	  /**
	* Used to rollback a transaction and catch any exceptions during the process.
	*
	* @param transaction
	* @throws RuntimeException
	*/
	  public static void rollbackTransaction(Transaction transaction) throws RuntimeException {
	    if (transaction != null && transaction.isActive()) {
	      try {
	        transaction.rollback();
	      } catch (HibernateException e) {
	        throw new RuntimeException("Error rolling back transaction. " + e.getMessage());
	      }
	    }
	  }
	  
	  public static void main(String[] args){
		  getSession();
	  }

}