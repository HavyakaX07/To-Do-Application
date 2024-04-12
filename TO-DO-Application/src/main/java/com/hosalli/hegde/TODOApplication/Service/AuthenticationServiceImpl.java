package com.hosalli.hegde.TODOApplication.Service;

import org.springframework.stereotype.Service;

import com.hosalli.hegde.TODOApplication.Models.LoginUserInfo;

/*
 * This file provide authentication service for logging in user
 * get the hash from the user management table and check against the computed hash of the logged in user.
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public boolean authenticateUser(LoginUserInfo user) {
		//As of now if it is root we will allow user to login
		if(user.getUserName().equals("root") && user.getPassword().equals("root")) {
			return true;
		}
		return false;
	}

}
