package com.tech.microservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tech.microservice.model.ExchangeValue;
import com.tech.microservice.repository.ExchangeValueRepository;

@RestController

public class CurrencyExchangeController {

	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exValue = exchangeValueRepository.findByFromAndTo(from, to);
		if(exValue != null)
			exValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exValue;
	}
}
