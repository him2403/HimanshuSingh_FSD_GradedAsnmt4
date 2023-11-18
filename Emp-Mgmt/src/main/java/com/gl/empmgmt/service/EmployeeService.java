package com.gl.empmgmt.service;

import java.util.List;

import com.gl.empmgmt.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(int theId);
	
	public List<Employee> searchEmployees(String keyword);

	public void save(Employee theEmp);

	public void deleteById(int theId);
		
	public List<Employee> sortEmployeesByFirstName(String order);

}
