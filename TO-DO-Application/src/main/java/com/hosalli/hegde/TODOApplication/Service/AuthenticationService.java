package com.hosalli.hegde.TODOApplication.Service;

import com.hosalli.hegde.TODOApplication.Models.LoginUserInfo;

import jakarta.validation.Valid;

public interface AuthenticationService {

	boolean authenticateUser(LoginUserInfo user);

}
