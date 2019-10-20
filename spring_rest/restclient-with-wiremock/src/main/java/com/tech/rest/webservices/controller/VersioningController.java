package com.tech.rest.webservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.rest.webservices.model.Name;
import com.tech.rest.webservices.model.PersonV1;
import com.tech.rest.webservices.model.PersonV2;

/*
 * 1. URI Versioning 
 * 2. Request Parameter Versioning
 * 3.
 * 
 * */

@RestController
public class VersioningController {

	@GetMapping("v1/person")
	public PersonV1 personV1() {
		 
		return new PersonV1("Rahul");
		
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		 
		return new PersonV2(new Name("Rahul","Kumar"));
		
	}
	
	@GetMapping(value="person/param", params="version=V1")
	public PersonV1 paramV1() {
		 
		return new PersonV1("Rahul");
		
	}
	
	@GetMapping(value="person/param", params="version=V2")
	public PersonV2 paramV2() {
		 
		return new PersonV2(new Name("Rahul","Kumar"));
		
	}
	
	@GetMapping(value="person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1h() {
		 
		return new PersonV1("Rahul");
		
	}
	
	@GetMapping(value="person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2() {
		 
		return new PersonV2(new Name("Rahul","Kumar"));
		
	}
}
