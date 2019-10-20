package com.tech.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tech.security.model.User;
import com.tech.security.model.UserPrincipal;
import com.tech.security.repo.UserRepository;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

	
}
