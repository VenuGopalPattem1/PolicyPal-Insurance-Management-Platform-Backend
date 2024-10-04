package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin
public class JrDataCollectionModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JrDataCollectionModuleApplication.class, args);
	}

}
