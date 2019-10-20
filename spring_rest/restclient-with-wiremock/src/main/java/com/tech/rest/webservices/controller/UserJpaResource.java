package com.tech.rest.webservices.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tech.rest.webservices.dao.PostRepository;
import com.tech.rest.webservices.dao.UserServiceJpa;
import com.tech.rest.webservices.exception.UserNotFoundExceptionException;
import com.tech.rest.webservices.model.Post;
import com.tech.rest.webservices.model.User;

@RestController
public class UserJpaResource {

	@Autowired
	private UserServiceJpa userService;
	
	@Autowired
	private PostRepository postRepository;
	//get /users
	//retrieveAllUser
	
    //get /users/{id}
	//retrieveUser(int id)
	
	@GetMapping(path= "/jpa/users")
	public List<User> retrieveAllUsers(){
		
		return userService.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUserById(@PathVariable Integer id) {
		
		User user = userService.findById(id);
		if(user == null)
		throw	new UserNotFoundExceptionException("id:- "+id);
		
		//HATEOAS Implementation
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		
		userService.deleteById(id);
		
		
	}
	
	@GetMapping(path= "/jpa/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable Integer id){
		User user = userService.findById(id);
		if(user == null)
			throw new UserNotFoundExceptionException("id:- "+id);
		 return user.getPosts();
		
	}
	
	@PostMapping(path= "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createUserPost(@PathVariable Integer id, @RequestBody Post post){
		User user = userService.findById(id);
		if(user == null)
			throw new UserNotFoundExceptionException("id:- "+id);
		
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
