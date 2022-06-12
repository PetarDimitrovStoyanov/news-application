package com.newsAapplicationMicroservice.authmicroservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MainMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainMicroserviceApplication.class, args);
		log.info("Server started successfully on port: 8002 - http://localhost:8002/");
	}

}
