package com.tech.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tech.security.model.JwtProperties;
import com.tech.security.model.User;
import com.tech.security.model.UserPrincipal;
import com.tech.security.repo.UserRepository;
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private UserRepository userRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager,UserRepository userRepository) {
		super(authenticationManager);
	    this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//Read the authorization header, where the jwt token should be
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		//if header does not contains Bearer or is null delegate to Spring impl and exit
		if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		//if header is present, try grab user principal from database and perform authorization
		Authentication authentication = getUserNamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//Continue filter execution
		chain.doFilter(request, response);
		
	}

	private Authentication getUserNamePasswordAuthentication(HttpServletRequest request) {

		String token = request.getHeader(JwtProperties.HEADER_STRING);
		if(token != null) {
			
			String userName = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
					.build()
					.verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
					.getSubject();
			//Search in the db if we find the user by token subject(username)
			//if so, then grab user details and create spring auth token using username, pass, authorities/roles
			if(userName != null) {
				
				User user = userRepository.findByUserName(userName);
				UserPrincipal userPrincipal = new UserPrincipal(user);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, userPrincipal.getAuthorities());
			    return auth;
			}
			return null;
		}
		
		return null;
	}
}
