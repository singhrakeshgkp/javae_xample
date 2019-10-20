package com.tech.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SpringbootOauth2AuthServerApplication {

	public static void main(String[] args) {
	
		
		SpringApplication.run(SpringbootOauth2AuthServerApplication.class, args);
		
		/*PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		boolean status = encoder.matches("password", "$2a$10$p6O1Yr7Op0xDPXfTaQXx.OSbEajF1jGLHWkbmSZSgrNZaqI81kWQm");
		System.out.println(status);*/
	}

}
