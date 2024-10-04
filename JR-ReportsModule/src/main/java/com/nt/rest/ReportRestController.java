package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.ElgibilityDetailsOutputs;
import com.nt.service.ElgiServiceImpl;

@RestController
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	private ElgiServiceImpl ser;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<ElgibilityDetailsOutputs>> getAllData(){
		List<ElgibilityDetailsOutputs> list=ser.getAllData();
		return new ResponseEntity<List<ElgibilityDetailsOutputs>>(list,HttpStatus.OK);
	}
}
