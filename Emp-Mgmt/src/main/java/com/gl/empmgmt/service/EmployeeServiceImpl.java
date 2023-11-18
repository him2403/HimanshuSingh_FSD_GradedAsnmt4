package com.gl.empmgmt.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.empmgmt.entity.Employee;
import com.gl.empmgmt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the Employee
			throw new RuntimeException("Employee Not Found with given Emp Number: " + theId);
		}
		return theEmployee;
	}

	@Override
	public void save(Employee theEmp) {
		// TODO Auto-generated method stub
		employeeRepository.save(theEmp);
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(theId);
	}

	@Override
	
	public List<Employee> searchEmployees(String keyword) {
		List<Employee> employees = employeeRepository.findByFnameContaining(keyword);
		return employees;
    }
	@Override
	public List<Employee> sortEmployeesByFirstName(String order) {
        List<Employee> employees = findAll();
        
        Comparator<Employee> comparator = Comparator.comparing(Employee::getFname, String.CASE_INSENSITIVE_ORDER);

        if ("asc".equalsIgnoreCase(order)) {
            employees.sort(comparator);
        } else {
            employees.sort(comparator.reversed());
        }

        return employees;
    }

}
