package com.tech.security;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tech.security.service.UserPrincipalDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserPrincipalDetailService userPrincipalDetailService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) {
	
		auth.authenticationProvider(authenticationProvider());
	}
	

	
	 @Override
		public void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/profile/**").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/management/**").hasAnyRole("ADMIN","MANAGER")
				.antMatchers("/api/public/test1").hasAuthority("Access_Test1")
				.antMatchers("/api/public/test2").hasAuthority("Access_Test2")
				.antMatchers("/api/public/users").hasRole("ADMIN")
				.and()
				.formLogin()
				.loginProcessingUrl("/signin")
				.loginPage("/login").permitAll()
				.usernameParameter("txtUserName")
				.passwordParameter("txtPassword")
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.and()
				.rememberMe().tokenValiditySeconds(2592000).key("abcsecret").rememberMeParameter("txtRememberMe");
				
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
