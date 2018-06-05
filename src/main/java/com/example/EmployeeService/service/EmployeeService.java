package com.example.EmployeeService.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.EmployeeService.Dao.EmployeeDao;
import com.example.EmployeeService.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
public List<Employee> getEmployeeList() {

		return (List<Employee>) employeeDao.findAll();
	}

	public Object getEmployee(String empid) {

		 if(employeeDao.existsById(empid))
			 return employeeDao.findById(empid);
			
		 return  null;
	}

	public void addEmployee(Employee e) {
		
		if(e.getCreatedDate() == null)
			e.setCreatedDate(new Date());

		employeeDao.save(e);
	}

	public List<Employee> getEmployeeByDesignation(String designation) {

       return employeeDao.findByDesignation(designation);
	}
	
	public List<Employee> lookupName(String name) {

	       return employeeDao.lookupName(name);
		}
		
	public List<Employee> getEmployeeByDesignationAndName(String designation, String name) {

	       return employeeDao.findByDesignationAndName(designation, name);
		}

	public List<Employee> lookupByDesignationAndName(String designation, String name) {
		// TODO Auto-generated method stub
		return employeeDao.LookupByDesignationAndName(designation, name);

	}
	
	@Async
	public CompletableFuture<Employee> ayncLookupEmpById(String eid){
		System.out.println("ayncLookupEmpById() : thread ->" + Thread.currentThread().getName() + " -- id :" +Thread.currentThread().getId());
		return employeeDao.findByEmpId(eid);
	}

	public List<Employee> getEmployeeByDesignationPagination(String designation, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return employeeDao.findByDesignation(designation,pageRequest);
	}

	

}
