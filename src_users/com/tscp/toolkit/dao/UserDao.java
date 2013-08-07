package com.tscp.toolkit.dao;

import java.util.List;

import com.tscp.toolkit.domain.user.User;

public interface UserDao {

	public List<User> getAllUsers();
	
	public User getUserByUserName(final String userName);
	
	public User getUserByEmail(final String email);
	
	public User getUserById(final int id);
	
	public void saveUser(User user);
	
	public void updateUser(User user);
	

}
