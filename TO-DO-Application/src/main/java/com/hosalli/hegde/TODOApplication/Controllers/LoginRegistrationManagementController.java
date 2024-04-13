package com.hosalli.hegde.TODOApplication.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hosalli.hegde.TODOApplication.Models.LoginUserInfo;
import com.hosalli.hegde.TODOApplication.Models.RegisterUser;
import com.hosalli.hegde.TODOApplication.Models.UserNamePojo;
import com.hosalli.hegde.TODOApplication.Service.AuthenticationService;
import com.hosalli.hegde.TODOApplication.Util.TodoApplicationUtil;

import jakarta.validation.Valid;

/*
 * This file manages 1. Welcome 2.Login 3. Registration 4. Logic success API end-points and Respective Views.
 */
@Controller
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
	/*
	 * Commenting this as it should be used with JS and not working as of now UI view is not supporting.
	 * Functionality is working fine but website is not rendering the view
	 * @RequestMapping(value = "loginUser", method = RequestMethod.POST, produces =
	 * "application/json")
	 * 
	 * @ResponseBody public String authenticateUser(@Valid @RequestBody
	 * LoginUserInfo user, BindingResult bindingReuslt, ModelMap model) {
	 * Map<String,String> responseJson = new HashMap<>(); if
	 * (bindingReuslt.hasErrors()) { // Redirect again to login page and show error
	 * responseJson.put("loginPageError",
	 * "Password or User name is not correct or adhere to constraint! Please try again."
	 * ); responseJson.put("loginStatus", "failed"); return
	 * TodoApplicationUtil.getJsonFromJava(responseJson); }
	 * 
	 * boolean isLoginSuccess = authenticationService.authenticateUser(user); if
	 * (isLoginSuccess) { // Return Manage to dos view with hello {username}.
	 * responseJson.put("username", user.getUserName());
	 * responseJson.put("loginStatus", "success"); return
	 * TodoApplicationUtil.getJsonFromJava(responseJson); }
	 * responseJson.put("loginPageError", "Login failed. Try again");
	 * responseJson.put("loginStatus", "failed"); return
	 * TodoApplicationUtil.getJsonFromJava(responseJson); }
	 */

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
			model.put("username", user.getUserName());
			return "manageTodosPage";
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

	@RequestMapping(value = "manageTodos", method = RequestMethod.POST)
	public String getTodosPage(@Valid @RequestBody UserNamePojo user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.error("Server side validation failed");
			return "login";
		}
		return "manageTodosPage";
	}

}
