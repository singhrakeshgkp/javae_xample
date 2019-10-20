package com.tech.rest.webservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tech.rest.webservices.model.User;

@Component
public class UserService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 4;
	static {
		
		users.add(new User(1, "shyam", new Date()));
		users.add(new User(2, "Ravi", new Date()));
		users.add(new User(3, "Ajay", new Date()));
		users.add(new User(4, "Rohan", new Date()));
	}
	
	public List<User> findAll(){
		
		return users;
	}
	
	public User findById(int id) {
		for(User user : users) {
			
			if(user.getId() == id)
				return user;
		}
		
	return null;	
	}
	
	
	public User save(User user) {
		
		if(user.getId() == null) {
			
			user.setId(++userCount);
		}
		
		users.add(user);
		return user;
	}
	
	
	public User deleteById(int id) {
		
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
			
		}
	return null;	
	}
	
}
