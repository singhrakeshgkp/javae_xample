package com.tech.rest.webservices.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.tech.rest.webservices.model.SomeBean;

/*
 * 
 * There are two ways to configure a filter, which are given below
 * 1. Static Filter:- this can be configure using @jsonIgnore property on the model
 * 2. Dynamic Filter :- Demonstration is given below
 * 
 * 
 * */
@RestController
public class FilteringController {

	@GetMapping("/some-bean")
	public MappingJacksonValue filterSomeBean() {
		
		SomeBean someBean = new SomeBean("value1","value2","value3");
		SimpleBeanPropertyFilter filters = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("someBean", filters);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filterProvider);
		return mapping;
		
	}
	
	@GetMapping("/list-of-beans")
	public MappingJacksonValue someBeans() {
		
		List<SomeBean> list = new ArrayList<SomeBean>(Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value11","value22","value33") ));
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("someBean", SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2"));
		mapping.setFilters(filterProvider);
		return mapping;
	}
	
}
