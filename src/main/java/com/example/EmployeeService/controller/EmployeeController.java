package com.example.EmployeeService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeService.model.Employee;
import com.example.EmployeeService.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@Api(value="EmployeeAPI",description="This is Employee service api")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@ApiOperation(value="get all Employees")
	@RequestMapping(value = "Employees", method = RequestMethod.GET)
	public List<Employee> getEmployees() {

		return empService.getEmployeeList();
	}

	@ApiOperation(value="get Employees details by emp id")
	@RequestMapping(value = "Employee/{eid}", method = RequestMethod.GET)
	public Employee getEmployeeDetails(@PathVariable(value = "eid") String eid) {

		return (Employee) empService.getEmployee(eid);

	}

	@ApiOperation(value="create employee")
	@RequestMapping(value = "Employee", method = RequestMethod.POST)
	public ResponseEntity<Object> CreateEmployee(@RequestBody Employee e) {
		
		empService.addEmployee(e);

		return new ResponseEntity<Object>("Employee created", HttpStatus.OK);

	}

}
