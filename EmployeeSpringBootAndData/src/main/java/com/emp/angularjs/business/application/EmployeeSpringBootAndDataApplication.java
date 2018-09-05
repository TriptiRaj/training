package com.emp.angularjs.business.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.emp.angularjs.business")
@EnableJpaRepositories("com.emp.angularjs.business.repository")
@EntityScan("com.emp.angularjs.business.entity")
public class EmployeeSpringBootAndDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSpringBootAndDataApplication.class, args);
	}
	
	
}
