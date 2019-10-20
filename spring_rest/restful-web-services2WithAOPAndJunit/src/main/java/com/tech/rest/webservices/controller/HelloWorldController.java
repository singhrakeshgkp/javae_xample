package com.tech.rest.webservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tech.rest.webservices.model.HelloWorld;

@RestController
public class HelloWorldController {

	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorld helloWorldBean() {
		
		return new HelloWorld("Hello World");
	}
	
	@GetMapping(path="/welcome-intl")
	public String welcomeINTL(@RequestHeader(name="accept-language")Locale locale) {
		
		return messageSource.getMessage("good.morning.message", null, locale);
	}
}
