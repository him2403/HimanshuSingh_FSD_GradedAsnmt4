package com.gl.empmgmt.dto;

import java.util.List;

public class UserRegistrationDto {

	private String username;
	private String password;
	private List<String> roles;

	public UserRegistrationDto(String username, String password, List<String> roles) {
		super();

		this.username = username;
		this.password = password;
		this.roles = roles;

	}

	public UserRegistrationDto() {
		super();
		// TODO Auto-generated constructor stub

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
