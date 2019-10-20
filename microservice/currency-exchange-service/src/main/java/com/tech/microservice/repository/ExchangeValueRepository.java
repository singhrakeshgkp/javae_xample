package com.tech.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.microservice.model.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from, String to);
}
