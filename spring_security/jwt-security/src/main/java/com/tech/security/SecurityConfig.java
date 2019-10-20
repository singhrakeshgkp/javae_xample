package com.tech.security;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.*;
import org.springframework.util.AntPathMatcher;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.AntPathRequestMatcherProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tech.security.repo.UserRepository;
import com.tech.security.service.UserPrincipalDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserPrincipalDetailService userPrincipalDetailService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) {
	
		auth.authenticationProvider(authenticationProvider());
	}
	

	
	 @Override
		public void configure(HttpSecurity http) throws Exception {
			http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
				.authorizeRequests()
				.antMatchers("/login").permitAll()
			    .antMatchers("/api/public/management/*").hasRole("MANAGER")
			    .antMatchers("/api/public/admin/*").hasRole("ADMIN");
				
		}
	
	 DaoAuthenticationProvider authenticationProvider() {
		 
		 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		 daoAuthenticationProvider.setPasswordEncoder(passwordEncoderEncoder());
		 daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailService);
		 return daoAuthenticationProvider;
	 }
	 
	
	@Bean
	PasswordEncoder passwordEncoderEncoder() {
		
		return new BCryptPasswordEncoder();
	}
}
