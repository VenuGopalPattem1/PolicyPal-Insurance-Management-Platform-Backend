package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.ElgibilityDetailsOutputs;
import com.nt.service.IElgbilityDetailsService;

@RestController
@RequestMapping("/ed")
public class EdRestController {
	
	@Autowired
	private IElgbilityDetailsService ser;
	
	@GetMapping("/check/{caseno}")
	public ResponseEntity<ElgibilityDetailsOutputs> checkEd(@PathVariable Integer caseno){
		ElgibilityDetailsOutputs output = ser.getTheEligibity(caseno);
		return new ResponseEntity<ElgibilityDetailsOutputs>(output,HttpStatus.OK);
	}
}
