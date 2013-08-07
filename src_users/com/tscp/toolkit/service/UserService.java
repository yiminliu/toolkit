package com.tscp.toolkit.service;

import com.tscp.toolkit.domain.user.User;

public interface UserService {

	public void updateUserNameAndEmail(String currentUserName, String newUserName);	
	
	public void updateUserName(String currentUserName, String newUserName);
		
	public void updateEmail(String currentEmail, String newEmail);
		
	public void resetPassword(String email, String newPassword);
	
	public void updatePassword(String email, String currentPassword, String newPassword);
		
	public User getUserByUserName(String userName);
	
	public User getUserByEmail(String email);
	
	public boolean confirmeUser(User user, String password);
	
	public void confirmPasswordUpdated(String inputPassword, String email);
	
}
