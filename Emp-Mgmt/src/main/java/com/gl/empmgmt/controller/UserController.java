package com.gl.empmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gl.empmgmt.dto.UserRegistrationDto;
import com.gl.empmgmt.entity.Role;
import com.gl.empmgmt.entity.User;
import com.gl.empmgmt.service.RoleService;
import com.gl.empmgmt.service.UserService;

@Controller
@RequestMapping("/register")
public class UserController {
	@Autowired
	UserService userService;
	
	 @Autowired
	    private RoleService roleService;

	@GetMapping
	public String showRegistrationForm(Model model) {
		
		 UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
	        model.addAttribute("userRegistrationDto", userRegistrationDto);
			
		List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

		return "register";
	}

	@PostMapping
	public String registerUser(@ModelAttribute("userRegistrationDto") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/register?success";
	}

}
