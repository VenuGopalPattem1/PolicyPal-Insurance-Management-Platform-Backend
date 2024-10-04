package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.CitizenAppRegistationIntput;
import com.nt.service.CitizenAppRegServiceImpl;

@RestController
@RequestMapping("/CR-api")
public class AppRegRestController {
	@Autowired
	private CitizenAppRegServiceImpl ser;
	
	@PostMapping("/regCitizen")
	public ResponseEntity<String> regUserAppliccation(@RequestBody CitizenAppRegistationIntput inputs){
		Integer appId = ser.registerCitizenApplication(inputs);
		return new ResponseEntity<String>("Citizen application is Register with id value "+appId,HttpStatus.OK);
	}
}
