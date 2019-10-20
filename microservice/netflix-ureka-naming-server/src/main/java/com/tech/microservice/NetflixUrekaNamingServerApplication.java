package com.tech.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixUrekaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixUrekaNamingServerApplication.class, args);
	}

}
