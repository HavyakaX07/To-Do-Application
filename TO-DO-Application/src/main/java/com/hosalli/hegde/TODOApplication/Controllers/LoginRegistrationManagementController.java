package com.hosalli.hegde.TODOApplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hosalli.hegde.TODOApplication.Models.LoginUserInfo;
import com.hosalli.hegde.TODOApplication.Service.AuthenticationService;

import jakarta.validation.Valid;

/*
 * This file manages 1. Welcome 2.Login 3. Registration 4. Logic success API end-points and Respective Views.
 */
@Controller
public class LoginRegistrationManagementController {
	
	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "TO-DO-APP", method = RequestMethod.GET)
	public String welcomeToTo_DOApp() {
		//Just return home page of TO-Do application
		return "welcomeHome" ;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLoginPage() {
		//Return login page jsp
		return "login";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String showRegistrationPage() {
		//Return login page jsp
		return "registration";
	}
	
	@RequestMapping(value = "loginUser", method = RequestMethod.POST)
	public String authenticateUser(@Valid @RequestBody LoginUserInfo user, BindingResult bindingReuslt,
			ModelMap model) {
		
		if (bindingReuslt.hasErrors()) {
			// Redirect again to login page and show error
			model.put("error", "Password or User name is not correct or adhere to constraint! Please try again.");
			return "login";
		}

		boolean isLoginSuccess = authenticationService.authenticateUser(user);
		if (isLoginSuccess) {
			// Return Manage to dos view with hello {username}.
			model.put("username", user.getUserName());
			return "manageTodos";
		}
		model.put("error", "Login failed. Try again");
		return "login";
	}

}
