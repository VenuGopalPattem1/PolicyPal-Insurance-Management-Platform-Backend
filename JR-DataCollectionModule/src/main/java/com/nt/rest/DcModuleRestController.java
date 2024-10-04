package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.ChildrenInputs;
import com.nt.binding.DcSummaryReports;
import com.nt.binding.EducationalInputs;
import com.nt.binding.IncomeInputs;
import com.nt.binding.PlanSelectionInputs;
import com.nt.service.DcMngServiceImpl;

@RestController
@RequestMapping("/dc")
public class DcModuleRestController {
	@Autowired
	private DcMngServiceImpl ser;
	
	@GetMapping("/case-no/{appId}")
	public ResponseEntity<Integer> generateCaseNumber(@PathVariable Integer appId){
		Integer caseNo = ser.generateCaseNumber(appId);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
	}
	
	@GetMapping("/getNames")
	public ResponseEntity<List<String>> getAllPlanNames(){
		List<String> caseNo = ser.getAllPlanNames();
		return new ResponseEntity<List<String>>(caseNo,HttpStatus.OK);
	}
	
	@PostMapping("/savePlan")
	public ResponseEntity<Integer> savePlanDetails(@RequestBody PlanSelectionInputs income){
		Integer caseNo = ser.savePlanDetails(income);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
	}
	
	@PostMapping("/saveChild")
	public ResponseEntity<Integer> saveChildDetails(@RequestBody List<ChildrenInputs> childs){
		Integer caseNo = ser.saveChildrensDetails(childs);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
	}
	
	@PostMapping("/saveIncome")
	public ResponseEntity<Integer> saveChildDetails(@RequestBody IncomeInputs income){
		Integer caseNo = ser.saveIncomeDetails(income);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
	}
	
	@PostMapping("/saveEducation")
	public ResponseEntity<Integer> saveChildDetails(@RequestBody EducationalInputs education){
		Integer caseNo = ser.saveEducationDetails(education);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
	}
	
	@GetMapping("/summary/{caseno}") 
	public ResponseEntity<DcSummaryReports> getSummaryReport(@PathVariable Integer caseno){
		DcSummaryReports summary = ser.getSummaryReport(caseno);
		return new ResponseEntity<DcSummaryReports>(summary,HttpStatus.OK);
	}
	
}
