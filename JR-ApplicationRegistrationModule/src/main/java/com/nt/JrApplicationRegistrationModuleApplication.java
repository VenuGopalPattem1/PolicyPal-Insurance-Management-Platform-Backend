package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JrApplicationRegistrationModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JrApplicationRegistrationModuleApplication.class, args);
	}

}
