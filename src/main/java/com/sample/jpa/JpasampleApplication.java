package com.sample.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpasampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpasampleApplication.class, args);
		System.out.println("Hello JPA Sample");
	}

}
