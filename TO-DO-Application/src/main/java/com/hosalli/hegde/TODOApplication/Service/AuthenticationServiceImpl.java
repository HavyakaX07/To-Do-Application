package com.hosalli.hegde.TODOApplication.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hosalli.hegde.TODOApplication.Models.LoginUserInfo;
import com.hosalli.hegde.TODOApplication.Models.RegisterUser;

/*
 * This file provide authentication service for logging in user
 * get the hash from the user management table and check against the computed hash of the logged in user.
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	@Override
	public boolean authenticateUser(LoginUserInfo user) {
		//As of now if it is root we will allow user to login
		if(user.getUserName().equals("root") && user.getPassword().equals("root")) {
			logger.info("Authentication passsed");
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean isUserNameAlreadyExist(RegisterUser user) {
		// query user management table and check user exist or not as of now return false
		return user.getUserName().equalsIgnoreCase("root");
	}
	@Override
	public void registerUser(RegisterUser user) {
		// save user information in data base.
		
	}


	@Override
	public boolean authenticateUser(String userName,String password) {
		if(userName.equals("root") && password.equals("root")) {
			logger.info("Authentication passsed");
			return true;
		}
		return false;
	}

}
