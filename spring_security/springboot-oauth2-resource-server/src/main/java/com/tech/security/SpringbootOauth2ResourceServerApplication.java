package com.tech.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class SpringbootOauth2ResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauth2ResourceServerApplication.class, args);
	}

}
