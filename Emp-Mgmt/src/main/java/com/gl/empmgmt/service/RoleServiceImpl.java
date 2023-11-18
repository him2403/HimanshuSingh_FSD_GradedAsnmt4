package com.gl.empmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.empmgmt.entity.Role;
import com.gl.empmgmt.repository.RoleRepository;
@Service
public class RoleServiceImpl implements RoleService{
@Autowired
	RoleRepository roleRepository;
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Role findByName(String rName) {
		// TODO Auto-generated method stub
		Optional<Role> result = roleRepository.findByName(rName);
		Role theRole = null;
		if (result.isPresent()) {
			theRole = result.get();
		} else {
			// we didn't find the Employee
			throw new RuntimeException("Role Not Found with given Role: " + rName);
		}
		return theRole;
	}

	@Override
	public void save(Role theRole) {
		// TODO Auto-generated method stub
		roleRepository.save(theRole);
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		roleRepository.deleteById(theId);
		
	}

}
