package com.example.EmployeeService.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@Data 
@NoArgsConstructor
@AllArgsConstructor 

/*@NamedQueries(value = 
{@NamedQuery(name="Employee.lookupName" , 
			 query="SELECT e from Employee e where name=?1") })*/
public class Employee implements Serializable{
	@Id
	private String empId;
	private String name;
	private String designation;
	private double salary;
	private Date createdDate;

	
}