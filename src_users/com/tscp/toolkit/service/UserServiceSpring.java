package com.tscp.toolkit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tscp.toolkit.dao.UserDao;
import com.tscp.toolkit.domain.user.User;
import com.tscp.toolkit.util.encryption.MD5Encoder;

@Service
public class UserServiceSpring implements UserService{
	
	
	@Autowired
	private UserDao userDao;
	
	public UserServiceSpring() {
		super();		
	}

	@Override
	public void updateUserNameAndEmail(String currentUserName, String newUserName){
		User user = userDao.getUserByUserName(currentUserName);
		user.setUsername(newUserName);
		user.setEmail(newUserName);
		userDao.updateUser(user);		
	}
	
	@Override
	public void updateUserName(String currentUserName, String newUserName){
		User user = userDao.getUserByUserName(currentUserName);
		user.setUsername(newUserName);
		userDao.updateUser(user);		
	}
	
	@Override
	public void updateEmail(String currentEmail, String newEmail){
		User user = userDao.getUserByEmail(currentEmail);
		user.setEmail(newEmail);
		userDao.updateUser(user);		
	}
		
	@Override
	public void resetPassword(String email, String newPassword){
		User user = userDao.getUserByEmail(email);
		user.setPassword(MD5Encoder.md5Encryption(newPassword));
		userDao.updateUser(user);	
	}
	
	@Override
	public void updatePassword(String email, String currentPassword, String newPassword){
		User user = userDao.getUserByEmail(email);
		if(!confirmeUser(user, currentPassword))			
	        throw new IllegalArgumentException("Incorrect User Information");
		
		user.setPassword(MD5Encoder.md5Encryption(newPassword));
		userDao.updateUser(user);	
	}
	
	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}
	
	@Override
	public User getUserByEmail(String email){
		return userDao.getUserByEmail(email);
	}	
	
	@Override
	public boolean confirmeUser(User user, String password){
	   return (user.getPassword() != null && user.getPassword().equalsIgnoreCase(MD5Encoder.md5Encryption(password)));
	}
	
	@Override
	public void confirmPasswordUpdated(String inputPassword, String email){
		
		User user = getUserByEmail(email);
		String newPassword = user.getPassword();
		System.out.println("New Password in hex = " + newPassword);
		if(MD5Encoder.md5Encryption(inputPassword).equals(newPassword))
			System.out.println("Password has been sucessfully updated");	
			
	}
}
