package com.eureka_config_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaConfigServiceApplication.class, args);
	}

}
