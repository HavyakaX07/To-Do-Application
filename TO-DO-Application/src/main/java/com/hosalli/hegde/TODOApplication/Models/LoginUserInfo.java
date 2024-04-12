package com.hosalli.hegde.TODOApplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
//Helps to give toString, getter setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUserInfo {
	@NotNull
	@NotEmpty
	@Size(max = 50)
	private String userName;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String password;
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


}
