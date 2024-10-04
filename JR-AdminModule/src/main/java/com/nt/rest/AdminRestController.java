package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.CaseWorkersInputs;
import com.nt.binding.PlanDataInputs;
import com.nt.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	@Autowired
	private AdminServiceImpl ser;
	
	@PostMapping("/save")
	public ResponseEntity<String> savePlayer(@RequestBody PlanDataInputs p){
		String msg=ser.savePlan(p);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<PlanDataInputs>> getAll(){
		List<PlanDataInputs> li=ser.getAllPlansData();
		return new ResponseEntity<List<PlanDataInputs>>(li, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id){
		String msg=ser.deletePlanById(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PlanDataInputs> getByid(@PathVariable Integer id){
		PlanDataInputs msg=ser.getPlanBYId(id);
		return new ResponseEntity<PlanDataInputs>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updatePlayer(@RequestBody PlanDataInputs p){
		String msg=ser.updatePlanDetals(p);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PatchMapping("/up/{id}/{status}")
	public ResponseEntity<String> updatePlayerStatus(@PathVariable Integer id ,
			                                                                                           @PathVariable String status){
		String msg=ser.changePlanStatus(id,status);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	//case workers related apis
	
	
	@PostMapping("/saveCw")
	public ResponseEntity<String> saveCw(@RequestBody CaseWorkersInputs p){
		String msg=ser.saveCW(p);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCw")
	public ResponseEntity<List<CaseWorkersInputs>> getAllCw(){
		List<CaseWorkersInputs> li=ser.getAllCWData();
		return new ResponseEntity<List<CaseWorkersInputs>>(li, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCw/{id}")
	public ResponseEntity<String> deleteCwById(@PathVariable Integer id){
		String msg=ser.deleteCW(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	@GetMapping("/getCw/{id}")
	public ResponseEntity<CaseWorkersInputs> getCwByid(@PathVariable Integer id){
		CaseWorkersInputs msg=ser.getCWById(id);
		return new ResponseEntity<CaseWorkersInputs>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/updateCw")
	public ResponseEntity<String> updateCw(@RequestBody CaseWorkersInputs p){
		String msg=ser.updateCWDetails(p);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PatchMapping("/upCw/{id}/{status}")
	public ResponseEntity<String> updateCwStatus(@PathVariable Integer id ,
			                                                                                           @PathVariable String status){
		String msg=ser.changeCWStatus(id,status);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	
}
