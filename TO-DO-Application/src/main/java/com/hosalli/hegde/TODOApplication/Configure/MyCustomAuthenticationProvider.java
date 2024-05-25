package com.hosalli.hegde.TODOApplication.Configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.hosalli.hegde.TODOApplication.Service.AuthenticationService;

@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider {

	private static Logger logger = LoggerFactory.getLogger(MyCustomAuthenticationProvider.class);
	
	private boolean isAuthenticationDone = false;
	
	private Authentication auth;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(!isAuthenticationDone) {
		logger.info("+++++++++++++++++++++++++++++++++++++!!!!!!!!!!!!!!!!!!!Inside MyCustomAuthenticationProvider");
		String userName = authentication.getName();
		String passWord = (String) authentication.getCredentials();
		logger.info("User name {} password {}",userName,passWord);
		boolean isLoginSuccess = authenticationService.authenticateUser(userName,passWord);
		if (isLoginSuccess) {
			logger.info("Login success !!!!!!!!!!!!!!");
			auth = authentication;
			isAuthenticationDone = true;
			return authentication;
		}
		return null;
		}
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		boolean isExist = authentication.equals(UsernamePasswordAuthenticationToken.class);
		logger.info("Inside support {}",isExist);
		return isExist;
	}

}
