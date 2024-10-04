package com.nt.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminCobtrollerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllexceptions(Exception exe){
		return new ResponseEntity<String>(exe.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
