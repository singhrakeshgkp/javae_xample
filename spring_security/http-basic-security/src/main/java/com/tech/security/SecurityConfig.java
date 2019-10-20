package com.tech.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth
			.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoderEncoder().encode("admin123")).roles("ADMIN")
			.and()
			.withUser("rakesh").password(passwordEncoderEncoder().encode("rakesh123")).roles("USER")
			.and()
			.withUser("manager").password(passwordEncoderEncoder().encode("manager123")).roles("MANAGER");
	}
	
//	1. Without role bassed configuration
	/*  @Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}*/
	
	//	2. Role based configuration
	
	 @Override
		public void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/profile/**").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/management/**").hasAnyRole("ADMIN","MANAGER")
				.antMatchers("/api/public/**").authenticated()  //Added the ant matchers to protect rest api
				.and()
				.httpBasic();
				
		}
	
	@Bean
	PasswordEncoder passwordEncoderEncoder() {
		
		return new BCryptPasswordEncoder();
	}
}
