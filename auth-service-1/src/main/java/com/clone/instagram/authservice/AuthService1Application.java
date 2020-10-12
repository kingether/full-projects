package com.clone.instagram.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableMongoAuditing
@Slf4j
public class AuthService1Application {

	public static void main(String[] args) {
		SpringApplication.run(AuthService1Application.class, args);
	}

}
