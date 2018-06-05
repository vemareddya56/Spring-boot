package com.example.EmployeeService.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class ActiveMQConfig {

	@Value("${activeMq.broker-url:tcp://localhost:61616}")
	private String brokerURL;

	@Bean
	Queue queue() {

		return new ActiveMQQueue("employee.queue");
	}

	@Bean
	ConnectionFactory activemqConnfactory() {

		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
		return factory;
	}

	@Bean
	JmsTemplate getjmsTemplate() {

		return new JmsTemplate(activemqConnfactory());
	}
}
