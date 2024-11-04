package com.proxy_config_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProxyConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyConfigServiceApplication.class, args);
	}

}
