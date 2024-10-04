package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
//	@Bean
//	public RestTemplate createTemplete() {
//		return new RestTemplate();
//	}
	
	@Bean("Web1")
	public WebClient createTemplete() {
		return  WebClient.create();
	}
}
