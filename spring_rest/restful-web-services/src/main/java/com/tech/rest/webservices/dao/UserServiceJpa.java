package com.tech.rest.webservices.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.rest.webservices.model.User;

@Service
public class UserServiceJpa {

	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> findAll(){
		
		return userRepository.findAll();
	}
	
	public User findById(int id) {
		
		
	Optional<User> user = 	userRepository.findById(id);
	return user.isPresent()?user.get():null;	
	}
	
	
	public User save(User user) {
		
		return userRepository.save(user);
	}
	
	
	public void deleteById(int id) {
		
	 userRepository.deleteById(id);	
	 
	}
	
}
