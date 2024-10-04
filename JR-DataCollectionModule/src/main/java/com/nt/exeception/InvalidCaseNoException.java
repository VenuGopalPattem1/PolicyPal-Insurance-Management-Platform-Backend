package com.nt.exeception;

public class InvalidCaseNoException extends RuntimeException {
	
	public InvalidCaseNoException() {
		super();
	}
	
	public InvalidCaseNoException(String msg) {
		super(msg);
	}
}
