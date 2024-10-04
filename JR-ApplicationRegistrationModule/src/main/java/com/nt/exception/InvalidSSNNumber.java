package com.nt.exception;

public class InvalidSSNNumber extends RuntimeException{
	
	public InvalidSSNNumber() {
		super();
	}
	
	public InvalidSSNNumber(String msg) {
		super(msg);
	}
}
