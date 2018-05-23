package com.smartbot.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ServiceIntegrationApplication {

	//private static final Logger logger = LoggerFactory.getLogger(ServiceIntegrationApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceIntegrationApplication.class, args);
	}
}
