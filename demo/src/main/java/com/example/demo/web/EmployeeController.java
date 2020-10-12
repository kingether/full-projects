package com.example.demo.web;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping(path = "demo")
public class EmployeeController {

	private EmployeeService employeeService;
	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
		this.employeeService = employeeService;
		this.employeeRepository = employeeRepository;
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Employee employee) {

		this.employeeService.save(employee);
	}
	
	@PutMapping("/edit")
	public Employee edit(@RequestBody Employee employee) {
		
		return this.employeeRepository.save(employee);
		
		
	}
	@DeleteMapping("/delete/{id}")
	public Employee delete(@PathVariable(name = "id") long id) {
		
		Employee e1 = this.employeeRepository.findById(id).orElseThrow(()
				->new NoSuchElementException("ID NOT FOUND!"));
		
		this.employeeRepository.deleteById(e1.getId());
		
		return e1;
		
	}
	
}
