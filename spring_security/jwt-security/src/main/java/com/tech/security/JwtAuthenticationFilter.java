package com.tech.security;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.security.model.JwtProperties;
import com.tech.security.model.LoginViewModel;
import com.tech.security.model.UserPrincipal;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		
		this.authenticationManager = authenticationManager;
	}
	
	/*Trigger when we issue post request to /login
	 * we also need to pass in {username and password in the request body}
	 * */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		//Grab credential and map them to loginviewmodel
		LoginViewModel credentials = null;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create login token
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				credentials.getUsername(),
				credentials.getPassword(),
				new ArrayList<>());
		
		//Authenticate User
		Authentication authentication = authenticationManager.authenticate(authToken);
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
		//Create new jwt token
		String token = JWT.create()
				.withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
		
		//add token in response
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+token);
	}

	
}
