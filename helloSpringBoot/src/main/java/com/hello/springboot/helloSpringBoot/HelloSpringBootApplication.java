package com.hello.springboot.helloSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application class. Application execution starts here.
 * 
 * @author Tripti 
 */
@SpringBootApplication(scanBasePackages={"com.hello.springboot.controller"})
public class HelloSpringBootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBootApplication.class, args);
	}
}
