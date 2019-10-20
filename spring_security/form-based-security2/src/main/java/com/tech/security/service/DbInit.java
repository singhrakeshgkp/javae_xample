package com.tech.security.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.security.entity.User;
import com.tech.security.repo.UserRepository;

@Service
public class DbInit implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	private UserRepository userRepository;
	
	 public DbInit(UserRepository repo) {
		this.userRepository = repo;
	}
	 
	@Override
	public void run(String... args) throws Exception {
		
		//Create user
		User dan = new User("dan", passwordEncoder.encode("dan123"), "User", "");
		User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1, ACCESS_TEST2");
		User manager = new User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");
		List<User> users = Arrays.asList(dan,admin,manager);
		//save to db
		this.userRepository.saveAll(users);
		
		
	}

	
}
