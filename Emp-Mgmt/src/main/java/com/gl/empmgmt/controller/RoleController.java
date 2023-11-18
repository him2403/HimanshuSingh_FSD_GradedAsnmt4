package com.gl.empmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gl.empmgmt.entity.Role;
import com.gl.empmgmt.service.RoleService;
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleService;
	
	@GetMapping("/list")
	public String getAllRole(Model model) {
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "role/role-list";
	}
	@GetMapping("/showRoleFormForAdd")
	public String showRoleFormForAdd(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);
		return "role/role-form";
	}
	@PostMapping("/saveRole")
	public String saveRole(@ModelAttribute("role") Role role) {
		// save the book.
		roleService.save(role);
		// use a redirect to prevent duplicate submissions
		return "redirect:/role/list";
	}
	@GetMapping("/showRoleFormForEdit/{name}")
	public String showRoleFormForUpdate(Model model, @PathVariable String name) {
		// Get the role from db...
		Role role = roleService.findByName(name);
		// set role as a model attribute to pre-populate the form
		model.addAttribute("role", role);
		return "role/role-form";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model) {
		try {
		        roleService.deleteById(id);
		        return "redirect:/role/list";
		    } catch (DataIntegrityViolationException e) {
		        // Foreign key violation, show error message
		        model.addAttribute("error", "Cannot delete role. Delete associated users first.");
		        return "redirect:/role/list";
		    }
	}


}
