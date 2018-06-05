package com.example.EmployeeService.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeService.model.Employee;

@RestController

@RequestMapping("/jms")
public class employeeJmsController {
	@Autowired
	Queue queue;

	@Autowired
	JmsTemplate jmsTemplate;

	@PostMapping("publish")
	public String publish(@RequestBody Employee emp) {

		emp.setCreatedDate(new Date());
		
		jmsTemplate.convertAndSend(queue, emp);
		
		return "published successfully";

	}

}
