package com.example.EmployeeService.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class profilesController {

	@Value("${greeting.message}")
	private String message;

	@RequestMapping(value="/greeting")
	public String greeting() {
		
		return message;
	}
}
