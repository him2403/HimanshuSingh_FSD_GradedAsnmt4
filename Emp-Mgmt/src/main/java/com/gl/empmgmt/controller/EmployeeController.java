package com.gl.empmgmt.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.empmgmt.entity.Employee;
import com.gl.empmgmt.service.EmployeeService;



@Controller
@RequestMapping("/employee")

public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/list")
	public String getAllEmployee(Model model) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		return "employee/employee-list";
	}

	@GetMapping("/showEmployeeFormForAdd")
	public String showEmployeeFormForAdd(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee/employee-form";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// save the book.
		employeeService.save(employee);
		// use a redirect to prevent duplicate submissions
		return "redirect:/employee/list";
	}

	@GetMapping("/showEmployeeFormForEdit/{id}")
	public String showEmployeeFormForUpdate(Model model, @PathVariable int id) {
		// Get the employee from db...
		Employee employee = employeeService.findById(id);
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "employee/employee-form";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		// delete the Employee
		employeeService.deleteById(id);
		// set employee as a model attribute to pre-populate the form
		return "redirect:/employee/list";
	}
	
	@GetMapping("/view/{id}")

	public String viewEmployee(@PathVariable int id, Model model) {
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		return "employee/view";
	}
	
	  @GetMapping("/search")
	
		public String searchEmployees(@RequestParam(name = "keyword", required = false) String keyword, @RequestParam(name = "sort", defaultValue = "asc") String sort, Model model) {
        List<Employee> matchingEmployees = employeeService.searchEmployees(keyword);
        Comparator<Employee> comparator = Comparator.comparing(Employee::getFname, String.CASE_INSENSITIVE_ORDER);

        if ("desc".equalsIgnoreCase(sort)) {
            matchingEmployees.sort(comparator.reversed());
        } else {
            matchingEmployees.sort(comparator);
        }
        model.addAttribute("employees", matchingEmployees);
        return "employee/search-results";
    }

   @GetMapping("/sort")
   public String sortEmployeesByFirstName(@RequestParam(defaultValue = "asc") String order, Model model) {
	    List<Employee> employees = employeeService.sortEmployeesByFirstName(order);
	    model.addAttribute("employees", employees);
	    return "employee/employee-list";
	}
}







