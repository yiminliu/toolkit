package com.tscp.toolkit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.domain.user.User;

@Repository
public class UserDaoSpring extends HibernateDaoSupport implements UserDao{
			
	public UserDaoSpring(){}
	
	@Autowired
	@Qualifier("hibernateTemplateMySql")
	  public void init(HibernateTemplate hibernateTemplate) {
	      setHibernateTemplate(hibernateTemplate);
	  }

	@Override
	@Transactional(readOnly=true)
	public List<User> getAllUsers(){
		return getHibernateTemplate().find("from User");
	}
	
	@Override
	@Transactional(readOnly=true)
	public User getUserByUserName(final String userName){
		String queryString = "from User where username = ?";
		List<User> userList = getHibernateTemplate().find(queryString, userName);
		if(userList == null || userList.size() <= 0) {
			return getUserByEmail(userName);		 //fail over	
		}
		else {
			return (User)userList.get(0);
		}				
	}
	
	@Override
	@Transactional(readOnly=true)
	public User getUserByEmail(final String email){
		String queryString = "from User where email = ?";
		List<User> userList = (List<User>)getHibernateTemplate().find(queryString, email);
		if(userList == null || userList.size() <= 0) {
			throw new IllegalArgumentException("No user found for the input: " + email);
		}
		else {
			return (User)userList.get(0);
		}		
	}
	
	@Override
	@Transactional(readOnly=true)
	public User getUserById(int id){
		return (User)getHibernateTemplate().get(User.class, id);
	}
	
	@Override
	@Transactional
	public void saveUser(User user){
		getHibernateTemplate().save(user);
	}
	
	@Override
	@Transactional
	public void updateUser(User user){
		getHibernateTemplate().saveOrUpdate(user);
	}	
	
}
