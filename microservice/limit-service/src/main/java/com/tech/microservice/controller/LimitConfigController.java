package com.tech.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.microservice.Configuration;
import com.tech.microservice.model.LimitConfiguration;

@RestController
public class LimitConfigController {

	@Autowired
	private Configuration configuration;

 @GetMapping("/limits")
 public LimitConfiguration retrieveLimitsFromConfiguration() {
	 
	 return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
 }
}
