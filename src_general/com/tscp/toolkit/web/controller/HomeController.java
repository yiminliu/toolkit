package com.tscp.toolkit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	//@Autowired
	//private UserManager userManager;
	
	@RequestMapping({ "/", "/home"})
	public String showToolkitHomePage(){
		return "home";
	}
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model){
      	return "login";    	
    }
    
   /* @RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
    	System.out.println("call to logout");
		return "redirect:/j_spring_security_logout";
	}
    */
	@RequestMapping(value = "timeout", method = RequestMethod.GET)
	public String showTimeout() {
		return "exception/timeout";
	}

    
    @RequestMapping (value = "/{appName}/index", method = RequestMethod.GET)
	public String showToolkitOption(@PathVariable("appName") String appName){
		return appName + "/index";
	}
    
    @RequestMapping(value = "/logoutSuccess", method=RequestMethod.GET)
    public String posLogout(){
       	return "redirect:logoutSuccess";
    }
    
}
