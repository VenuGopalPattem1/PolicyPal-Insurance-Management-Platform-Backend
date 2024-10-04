package com.nt.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.exception.InvalidSSNNumber;

@RestControllerAdvice
public class CitizenAppControlerAdvice {
	
	@ExceptionHandler(InvalidSSNNumber.class)
	public ResponseEntity<String> hanldeSSNNumberException(InvalidSSNNumber ssn){
		return new ResponseEntity<String>(ssn.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception ssn){
		return new ResponseEntity<String>(ssn.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
