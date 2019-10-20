package com.tech.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.security.model.User;
import com.tech.security.repo.UserRepository;

@RestController
@RequestMapping("api/public")
public class PublicRestApiController {

	@Autowired
	private UserRepository userRepository;
	
	
    public PublicRestApiController(){}

    @GetMapping("test1")
    public String test1(){
        return "API Test 1";
    }

    @GetMapping("test2")
    public String test2(){
        return "API Test 2";
    }
    
    @GetMapping("users")
    public List<User> allUsers(){
    	
    	return userRepository.findAll();
    	
    }
    
    @GetMapping("management/reports")
    public String reports(){
        return "Some report data";
    }

    // Available to ROLE_ADMIN
    @GetMapping("admin/users")
    public List<User> users(){
        return this.userRepository.findAll();
    }

}
