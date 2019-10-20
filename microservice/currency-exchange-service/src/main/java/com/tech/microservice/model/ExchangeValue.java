package com.tech.microservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ExchangeValue {

 @Id
 private Long id;
 
 @Column(name = "curr_from")
 private String from;
 
 @Column(name = "curr_to")
 private String to;
 
 @Column(name= "conversion_multiple")
 private BigDecimal conversionMultiple;
 
 @Transient
 private int port;
 
public ExchangeValue() {
	super();
	
}

public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple, int port) {
	super();
	this.id = id;
	this.from = from;
	this.to = to;
	this.conversionMultiple = conversionMultiple;
	this.port = port;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public BigDecimal getConversionMultiple() {
	return conversionMultiple;
}
public void setConversionMultiple(BigDecimal conversionMultiple) {
	this.conversionMultiple = conversionMultiple;
}

public int getPort() {
	return port;
}

public void setPort(int port) {
	this.port = port;
} 
 
}
