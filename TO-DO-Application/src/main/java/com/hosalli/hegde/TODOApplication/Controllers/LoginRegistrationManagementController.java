package com.hosalli.hegde.TODOApplication.Controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hosalli.hegde.TODOApplication.Models.LoginUserInfo;
import com.hosalli.hegde.TODOApplication.Models.RegisterUser;
import com.hosalli.hegde.TODOApplication.Service.AuthenticationService;
import com.hosalli.hegde.TODOApplication.Util.TodoApplicationUtil;

/*
 * This file manages 1. Welcome 2.Login 3. Registration 4. Logic success API end-points and Respective Views.
 */
@Controller
@SessionAttributes("loggedInUser")
public class LoginRegistrationManagementController {

	private static Logger logger = LoggerFactory.getLogger(LoginRegistrationManagementController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "TO-DO-APP", method = RequestMethod.GET)
	public String welcomeToTo_DOApp() {
		// Just return home page of TO-Do application
		return "welcomeHomePage";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLoginPage() {
		// Return login page jsp
		return "loginPage";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String showRegistrationPage() {
		// Return login page jsp
		return "registrationPage";
	}

	@RequestMapping(value = "loginUser", method = RequestMethod.POST)
	public String authenticateUser(@RequestParam("userName") String userName, @RequestParam("password") String password,
			ModelMap model) {
		LoginUserInfo user = new LoginUserInfo(userName, password);
		if (!TodoApplicationUtil.validateBeanContraint(user)) {
			// Redirect again to login page and show error
			model.put("error", "Login Failed! Try again");
			return "loginPage";
		}

		boolean isLoginSuccess = authenticationService.authenticateUser(user);
		if (isLoginSuccess) {
			// Return Manage to dos view with hello {username}.
			model.put("loggedInUser", user.getUserName());
			model.addAttribute("todos", null);
			return "redirect:manageTodos";
		}

		model.put("error", "Login Failed! Try again");
		return "loginPage";
	}

	@RequestMapping(value = "registerUser", method = RequestMethod.POST)
	public String registerUser(@RequestParam("userName")String username,@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,ModelMap model) {
		RegisterUser user = new RegisterUser(username,password,confirmPassword);
		if (!TodoApplicationUtil.validateBeanContraint(user)) {
			logger.error("Server side validation failed");
			model.put("error", "Registration Failed!");
			return "registrationPage";
		}

		if (!user.getConfirmPassowrd().equals(user.getPassword())) {
			model.put("error", "Registration Failed! Check password");
			return "registrationPage";
		}

		if (authenticationService.isUserNameAlreadyExist(user)) {
			model.put("error", "Registration Failed! User Already Exist");
			return "registrationPage";
		}

		authenticationService.registerUser(user);
		return "loginPage";
	}


}
