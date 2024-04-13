package com.hosalli.hegde.TODOApplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUser{
	
	@NotNull
	@NotEmpty
	@Size(max = 50)
	private String userName;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String password;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String confirmPassowrd;
	public RegisterUser(String username, String password, String confirmPassword) {
		this.userName = username;
		this.password = password;
		this.confirmPassowrd = confirmPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassowrd() {
		return confirmPassowrd;
	}
	public void setConfirmPassowrd(String confirmPassowrd) {
		this.confirmPassowrd = confirmPassowrd;
	}
	
	

}
