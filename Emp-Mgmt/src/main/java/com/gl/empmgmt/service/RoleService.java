package com.gl.empmgmt.service;

import java.util.List;

import com.gl.empmgmt.entity.Role;



public interface RoleService {
	

	public List<Role> findAll();

	public Role findByName(String rName);

	public void save(Role theRole);

	public void deleteById(int theId);

}
