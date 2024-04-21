package com.allengroup.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("Value: " + 1L);
		SpringApplication.run(DemoApplication.class, args);
	}

}
