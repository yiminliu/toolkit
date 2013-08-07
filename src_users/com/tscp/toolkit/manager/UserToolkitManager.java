package com.tscp.toolkit.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tscp.toolkit.domain.user.User;
import com.tscp.toolkit.exception.UserToolkitException;
import com.tscp.toolkit.service.UserService;
import com.tscp.toolkit.service.UserServiceSpring;

@Component	
public class UserToolkitManager {
		
	private String appName;
		
		//@Autowired
		//@Qualifier("sessionFactory")
		//protected SessionFactory sessionFactory;
		
		@Autowired
		private UserService userService;
		
		public UserToolkitManager(){
		 //  init();
		}
		
		public UserToolkitManager(String appName){
			this.appName = appName;
  		  //  init();
		}
				
		public void resetPassword(String email, String newPassword) throws UserToolkitException{
			try{
				userService.resetPassword(email, newPassword);
				userService.confirmPasswordUpdated(newPassword, email);
				System.out.println("Password has been updated");
			}
			catch(Exception e){
			  e.printStackTrace();
			  throw new UserToolkitException(e.getMessage());
		    }
		}
		
		public void updatePassword(String email, String currentPassword, String newPassword) throws UserToolkitException{
			try{
				userService.updatePassword(email, currentPassword, newPassword);
				userService.confirmPasswordUpdated(newPassword, email);
				System.out.println("Password has been updated");
			}
			catch(Exception e){
			  e.printStackTrace();
			  throw new UserToolkitException(e.getMessage());
		    }
		}
				
		public void updateEmail(String currentEmail, String newEmail) throws UserToolkitException{
			try{
			   userService.updateEmail(currentEmail, newEmail);
			   System.out.println("User has been updated");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void updateUserName(String currentUserName, String newUserName) throws UserToolkitException{
			userService.updateEmail(currentUserName, newUserName);
		}
		
		public void updateuserNameAndEmail(String currentEmail, String newEmail){
			userService.updateUserNameAndEmail(currentEmail, newEmail);
			System.out.println("updateuserNameAndEmail Done");
		}
		
		public User getUserByEmail(String email){
			return userService.getUserByEmail(email);
		}
		
		
		//@Transactional(readOnly = true)
		public User getUserByUsername(
				String username) {
			return userService.getUserByUserName(username);
		}
		
				
		/*private void init(){
			ApplicationContext ctx = null;;
			if (appName.equalsIgnoreCase("TSCPMVNE"))
			    ctx = new ClassPathXmlApplicationContext("tscpmvne_toolkit-context.xml");
			else if (appName.equalsIgnoreCase("TSCPMVNA"))
				ctx = new ClassPathXmlApplicationContext("tscpmvna_toolkit-context.xml");
		
			userService = (UserServiceSpring)ctx.getBean("userServiceSpring");		
		}
	    */
}
