package com.tech.rest.webservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception{
		
    ExceptionResponse exResponse =	new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
       return  new ResponseEntity(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
    @ExceptionHandler(UserNotFoundExceptionException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptionException(Exception ex, WebRequest request) throws Exception{
		
    ExceptionResponse exResponse =	new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
       return  new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
	}
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) { 
				
		ExceptionResponse response = new ExceptionResponse(new Date(), "Validation failled", ex.getBindingResult().toString());
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}
	
}
