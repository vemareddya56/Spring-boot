package com.example.EmployeeService.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeService.model.Employee;
import com.example.EmployeeService.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/jpa")
@Api(value = "EmployeeAPI_JPA", description = "This is Employee service api JPA")
public class EmployeeController_JPA {

	@Autowired
	EmployeeService empService;

	@ApiOperation(value = "get Employees by designation")
	@RequestMapping(value = "Employee/{designation}", method = RequestMethod.GET)
	public List<Employee> getEmployeeDetails(@PathVariable(value = "designation") String designation) {

		return empService.getEmployeeByDesignation(designation);

	}

	@ApiOperation(value = "get Employees by designation")
	@RequestMapping(value = "lookup/{name}", method = RequestMethod.GET)
	public List<Employee> lookupName(@PathVariable(value = "name") String name) {
		System.out.println("invocation : thread ->" + Thread.currentThread().getName() + " -- id :"
				+ Thread.currentThread().getId());
		return empService.lookupName(name);

	}

	@ApiOperation(value = "get Employees by designation")
	@RequestMapping(value = "lookupByParam", method = RequestMethod.GET)
	public List<Employee> lookupByDesignationAndName(@RequestParam(name = "designation") String designation,
			@RequestParam(name = "name") String name) {

		return empService.lookupByDesignationAndName(designation, name);

	}

	@ApiOperation(value = "get Employees by designation and name with request param")
	@RequestMapping(value = "Employee", method = RequestMethod.GET)
	public List<Employee> getEmployeeDetailsByParam(@RequestParam(name = "designation") String designation,
			@RequestParam(name = "name") String name) {
		System.out.println("Parameters :" + designation + " and " + name);
		return empService.getEmployeeByDesignationAndName(designation, name);

	}

	@ApiOperation(value = "get Employees by id with async execution")
	@RequestMapping(value = "async/Employee/{eid}", method = RequestMethod.GET)
	public Employee AyncgetEmployeebyId(@PathVariable(name = "eid") String eid)
			throws InterruptedException, ExecutionException, TimeoutException {
		
		CompletableFuture<Employee> completableFuture = empService.ayncLookupEmpById(eid);
		System.out.println("async invocation and before get : thread ->" + Thread.currentThread().getName() + " -- id :"
				+ Thread.currentThread().getId());

		Employee e = completableFuture.get(20, TimeUnit.SECONDS);
		System.out.println("async invocation and get : thread ->" + Thread.currentThread().getName() + " -- id :"
				+ Thread.currentThread().getId());
		return e;

	}
	
	
	
	@ApiOperation(value = "get Employees by designation and name with pagination request param")
	@RequestMapping(value = "Pagination", method = RequestMethod.GET)
	public List<Employee> getEmployeeDetailsByPagination(@RequestParam(name = "designation") String designation,
			@RequestParam(name = "pageNo") int pageNo,@RequestParam(name = "pageSize") int pageSize) {
		//System.out.println("Parameters :" + designation + " and " + name);
		//empService.getEmployeeByDesignationPagination(designation, new PageRequest(pageNo, pageSize, Direction.ASC, "name"));
		return empService.getEmployeeByDesignationPagination(designation, new PageRequest(pageNo, pageSize, Direction.ASC, "name"));
	}
}
