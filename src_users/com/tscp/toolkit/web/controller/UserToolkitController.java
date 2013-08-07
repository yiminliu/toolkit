package com.tscp.toolkit.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.tscp.toolkit.domain.user.User;
import com.tscp.toolkit.exception.UserToolkitException;
import com.tscp.toolkit.manager.UserToolkitManager;
import com.tscp.toolkit.service.UserService;
import com.tscp.toolkit.web.validator.UserValidator;

@Controller
@RequestMapping("/{appName}/user")
//@SessionAttributes({"user"})
public class UserToolkitController {

	@Autowired
	UserService userService;
	@Autowired
	private UserToolkitManager userToolkitManager;	
	@Autowired
	private UserValidator userValidator;
				
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public String showUpdatePassword(Model model, @PathVariable("appName") String appName){
       model.addAttribute("user", new User());
       return appName + "/user/updatePassword";
	}	
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	public ModelAndView processUpdatePassword(@ModelAttribute("user") User user, BindingResult result, 
			                            @PathVariable("appName") String appName, SessionStatus status) throws Exception{
		ModelAndView model = new ModelAndView();
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			model.setViewName(appName + "/user/updatePassword");
			return model;
		}
		try {
		    userToolkitManager.updatePassword(user.getEmail(), user.getPassword(), user.getNewPassword());
		    status.setComplete();
		    model.setViewName(appName + "/user/updateSuccess");
	        model.addObject("key", "password");
	   	    return model;
		}
		catch(UserToolkitException ute){
		   ute.printStackTrace();
		   throw ute;
		} 				   
	 }
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String showResetPassword(Model model, @PathVariable("appName") String appName){
		model.addAttribute("user", new User());
		return appName + "/user/resetPassword";
	}
	
	@RequestMapping( value = "resetPassword", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	public ModelAndView processResetPassword(@ModelAttribute("user") User user, BindingResult result, 
			                                @PathVariable("appName") String appName, SessionStatus status) throws Exception{
		ModelAndView model = new ModelAndView();
		userValidator.validate(user, result);
		if(result.hasErrors()){
			model.setViewName(appName + "/user/resetPassword");
			return model;
		}
		try {
	         userToolkitManager.resetPassword(user.getEmail(), user.getPassword());
	         model.setViewName(appName + "/user/updateSuccess");
	         model.addObject("key", "password");
			 status.setComplete();
	         return model;
	  	}
		catch(UserToolkitException ute){
			ute.printStackTrace();
			throw new Exception(ute.getMessage());
		}		
	}
	
	@RequestMapping(value = "/updateUsernameAndEmail", method = RequestMethod.GET)
	public String showUpdateUsernameAndEmail (Model model, @PathVariable("appName") String appName){
		model.addAttribute("user", new User());
		return appName + "/user/updateUsernameAndEmail";
	}	
	
	@RequestMapping(value = "/updateUsernameAndEmail", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	public ModelAndView processUpdateUsernameAndEmail(@ModelAttribute("user") User user, BindingResult result, 
			                                    @PathVariable("appName") String appName, SessionStatus status){
		ModelAndView model = new ModelAndView();
		userValidator.validate(user, result);
		if(result.hasErrors()){
			model.setViewName(appName + "/user/updateUsernameAndEmail");
			return model;
		}
		try {
			 userToolkitManager.updateuserNameAndEmail(user.getEmail(), user.getNewEmail());
			 model.setViewName(appName + "/user/updateSuccess");
			 model.addObject("key", "Username and Email");
			 model.addObject("value", user.getNewEmail());
			 status.setComplete();
			 return model;
		}
		catch(UserToolkitException e){
			throw new RuntimeException(e.getMessage());
		}
		catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

}
	/*@RequestMapping(value="/updateEmail", method=RequestMethod.GET)
	public String showUpdateEmail(@ModelAttribute("User") User user, BindingResult result){
	   //boolean updateUserName = ;
	   if(updateUserName)
		   userService.updateUserNameAndEmail(currentUserName, newUserName);
	   else
		   userService.updateEmail(currentEmail, newEmail);
	   return "/user/email";
	}
	}
	*/
	

