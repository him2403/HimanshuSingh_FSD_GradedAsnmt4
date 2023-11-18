package com.gl.empmgmt.service;

import com.gl.empmgmt.dto.UserRegistrationDto;
import com.gl.empmgmt.entity.User;

public interface UserService {
	public User save(UserRegistrationDto registrationDto);

}
