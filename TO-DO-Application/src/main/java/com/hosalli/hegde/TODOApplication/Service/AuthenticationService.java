package com.hosalli.hegde.TODOApplication.Service;

import com.hosalli.hegde.TODOApplication.Models.LoginUserInfo;
import com.hosalli.hegde.TODOApplication.Models.RegisterUser;

import jakarta.validation.Valid;

public interface AuthenticationService {

	boolean authenticateUser(LoginUserInfo user);

	boolean isUserNameAlreadyExist(RegisterUser user);

	void registerUser(RegisterUser user);

	boolean authenticateUser(String userName,String password);

}
