package com.tech.microservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tech.microservice.CurrencyExchangeProxy;
import com.tech.microservice.model.CurrencyConversionBean;

@RestController
public class CurrencyConverterController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	

	
	/*
	 *  First way to call a rest service
	 * */
	
	@GetMapping("/currency-conversion-service/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> variableValues = new HashMap<>();
		variableValues.put("from", from);
		variableValues.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange-service/from/{from}/to/{to}", CurrencyConversionBean.class,
				variableValues);
		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(1001L, from, to, response.getConversionMultiple(), quantity,
				(quantity.multiply(response.getConversionMultiple())), response.getPort());

	}
	
	/*
	 *  Second way to call a rest service
	 * */
	
	@GetMapping("/currency-conversion-service-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyUsingFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		return new CurrencyConversionBean(1001L, from, to, response.getConversionMultiple(), quantity,
				(quantity.multiply(response.getConversionMultiple())), response.getPort());

	}
}
