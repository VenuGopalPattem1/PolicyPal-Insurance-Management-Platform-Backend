package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.CoTriggersOutput;
import com.nt.service.ICoTriggersService;

@RestController
@RequestMapping("/co")
public class CoRestController {
	@Autowired
	private ICoTriggersService ser;
	
	@GetMapping("/trigger")
	public ResponseEntity<?> getTriggerMsg(){
		try {
			CoTriggersOutput ct = ser.checkTriggers();
			return new ResponseEntity<CoTriggersOutput>(ct,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
