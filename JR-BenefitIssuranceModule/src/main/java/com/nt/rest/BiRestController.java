package com.nt.rest;

import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.IBenefitIssuranceService;

@RestController
@RequestMapping("/bi")
public class BiRestController {
	@Autowired
	private IBenefitIssuranceService ser;
	
	@GetMapping("path")
	public ResponseEntity<String> getMethodName() {
		try {
			JobExecution ex = ser.sendBenifitAmount();
			return new ResponseEntity<String>(ex.getExitStatus().getExitDescription(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);

		}}
	
}
