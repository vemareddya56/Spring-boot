package com.example.EmployeeService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.EmployeeService.model.Employee;
import com.example.EmployeeService.service.EmployeeService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages="com.example.EmployeeService")

public class SpringBootEmployeeServiceApplication implements CommandLineRunner {

	@Autowired
	EmployeeService employeeService;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmployeeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("before async invocation : thread ->" + Thread.currentThread().getName() + " -- id :"
				+ Thread.currentThread().getId());

			
		CompletableFuture<Employee> completableFuture = employeeService.ayncLookupEmpById("E3");
		System.out.println("async invocation and before get : thread ->" + Thread.currentThread().getName() + " -- id :"
				+ Thread.currentThread().getId());

		Employee e = completableFuture.get(20, TimeUnit.SECONDS);
		System.out.println("async invocation and get : thread ->" + Thread.currentThread().getName() + " -- id :"
				+ Thread.currentThread().getId());
		
	}
}
