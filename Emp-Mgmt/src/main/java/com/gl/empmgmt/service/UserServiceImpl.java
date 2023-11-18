package com.gl.empmgmt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.empmgmt.config.CustomUserDetails;
import com.gl.empmgmt.dto.UserRegistrationDto;
import com.gl.empmgmt.entity.Role;
import com.gl.empmgmt.entity.User;
import com.gl.empmgmt.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private RoleService roleService;
	
	@Override
	public User save(UserRegistrationDto registrationDto) {
		// TODO Auto-generated method stub
		 List<String> selectedRoleNames = registrationDto.getRoles();
		 List<Role> selectedRoles = selectedRoleNames.stream()
			        .map(roleService::findByName)
			        .collect(Collectors.toList());
		 
		User user = new User(registrationDto.getUsername(),
			passwordEncoder.encode(registrationDto.getPassword()), selectedRoles);

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Invalid Username or Passowrd.");
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails;
	}

	
}
