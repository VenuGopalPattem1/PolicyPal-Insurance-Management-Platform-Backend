package com.nt.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ssn")
public class SsaRestController {
	
	@GetMapping("/check/{num}")
	public ResponseEntity<String> checkSSNNumber(@PathVariable Integer num){
		if(String.valueOf(num).length()!=9)
		return new ResponseEntity<String>("Invalid SSN Number",HttpStatus.BAD_REQUEST);
		Integer val = num%100;
		String state=null;
		if(val ==01) {
			state ="Ohio";
		}else if(val== 02) {
			state = "WashingTon DC";
		}else if(val == 03) {
			state = "Texas";
		}else if(val == 04) {
			state = "California";
		}else if(val == 05) {
			state = "Florida ";
		}else {
			state = "Invalid";
		}
		return new ResponseEntity<String>(state,HttpStatus.CREATED);
	}
}
