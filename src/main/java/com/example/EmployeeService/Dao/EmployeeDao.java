package com.example.EmployeeService.Dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.example.EmployeeService.model.Employee;

@Repository
//public interface EmployeeDao extends CrudRepository<Employee, String> {
	
	public interface EmployeeDao extends CrudRepository<Employee, String> {

	@Query(value="SELECT e from Employee e where name=?1")
	public List<Employee> lookupName(String name);
	
	//@Query(value="SELECT e from Employee e where name=?1")
	//public List<Employee> findBy
	
	//@Async
	public CompletableFuture<Employee> findByEmpId(String eid);
	
	
	public List<Employee> findByDesignation(String designation);
	
	public List<Employee> findByDesignationAndName(String designation, String name);
	
	@Query(value="SELECT e from Employee e where name=:p_name and designation=:p_designation")
	public List<Employee> LookupByDesignationAndName(@Param("p_designation") String designation, @Param("p_name") String name);

	public List<Employee> findByDesignation(String designation, Pageable pageable);
	
}
