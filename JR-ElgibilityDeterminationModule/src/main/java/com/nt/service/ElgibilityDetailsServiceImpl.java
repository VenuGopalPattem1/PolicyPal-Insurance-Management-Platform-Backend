package com.nt.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.ElgibilityDetailsOutputs;
import com.nt.entity.CitizenAppRegistartionEntity;
import com.nt.entity.CoTriggersEntity;
import com.nt.entity.DcCasesEntity;
import com.nt.entity.DcChildrenEntity;
import com.nt.entity.DcIncomeEntity;
import com.nt.entity.ElgibilityDetailsEntity;
import com.nt.entity.PlanEntity;
import com.nt.exeception.InvalidCaseNoException;
import com.nt.repo.ICitizenAppRepo;
import com.nt.repo.ICoTriggersRepo;
import com.nt.repo.IDcCaseRepo;
import com.nt.repo.IDcChildrenRepo;
import com.nt.repo.IDcIncomeRepo;
import com.nt.repo.IElgibilityDetailsRepo;
import com.nt.repo.IPlanDetalsRepo;

@Service
public class ElgibilityDetailsServiceImpl implements IElgbilityDetailsService {
	@Autowired
	private IDcCaseRepo caseRepo;
	@Autowired
	private IPlanDetalsRepo planRepo;
	@Autowired
	private ICitizenAppRepo citizenRepo;
	@Autowired
	private IDcIncomeRepo incomeRepo;
	@Autowired
	private IDcChildrenRepo childRepo;
	@Autowired
	private IElgibilityDetailsRepo edRepo;
	@Autowired
	private ICoTriggersRepo coRepo;
	
	@Override
	public ElgibilityDetailsOutputs getTheEligibity(Integer caseno) {
		// get the planid and appid
		Integer planId=null;
		Integer appId=null;
		//check whether the caseNo is already present in elgidetails table or not
		Optional<ElgibilityDetailsEntity> elgi=edRepo.findByCaseNo(caseno);
		if(elgi.isPresent()) {
			ElgibilityDetailsOutputs ed= new ElgibilityDetailsOutputs();
			ElgibilityDetailsEntity et=elgi.get();
			BeanUtils.copyProperties(et, ed);
			return ed;
		}
		else {
			//get the appid and planid details from dccase Entity
			Optional<DcCasesEntity> opt = caseRepo.findById(caseno);
			if(opt.isPresent()) {
				DcCasesEntity caseEntity = opt.get();
				planId = caseEntity.getPlanId();
				appId = caseEntity.getAppId();
			}else {
				throw new InvalidCaseNoException("Invalid Case Number");
			}
			//get the plan name with the help of the plan id
			String planName = null;
			Optional<PlanEntity> plan = planRepo.findById(planId);
			if(plan.isPresent()) {
				PlanEntity planEntity = plan.get();
				planName = planEntity.getPlanName();
			}
			//get the citizen name and citizen age 
			Integer citizenAge = null;
			String citizenName = null;
			Long citizenSsn = null;
			Optional<CitizenAppRegistartionEntity> citizen =  citizenRepo.findById(appId);	
			if(citizen.isPresent()) {
				CitizenAppRegistartionEntity entity = citizen.get();
				citizenName = entity.getFullName();
				citizenSsn = entity.getSsn();
				LocalDate date = entity.getDob();
				LocalDate now = LocalDate.now();
				citizenAge = Period.between(date, now).getYears();
			}
			ElgibilityDetailsOutputs outputs = checkPlanRules(caseno,citizenAge,planName);
			outputs.setHolderName(citizenName);
			outputs.setHolderSsn(citizenSsn);
			//set the Eligibility detals objet values
			ElgibilityDetailsEntity ed = new  ElgibilityDetailsEntity();
			BeanUtils.copyProperties(outputs,ed);
			edRepo.save(ed);
			//set the cotrigger entity class values
			
			CoTriggersEntity co = new CoTriggersEntity();
			co.setCaseNo(caseno);
			coRepo.save(co);
			
			
			return outputs;

		}
	}
	
	private ElgibilityDetailsOutputs checkPlanRules(Integer caseno,Integer citizenAge,String planName) {
		             
		//create a ElgibilityDetailsOutputs class object
		ElgibilityDetailsOutputs ed = new ElgibilityDetailsOutputs();
		ed.setPlanName(planName);
		ed.setCaseNo(caseno);
		
		//get the income details of the citizen
		DcIncomeEntity income = incomeRepo.findByCaseNumber(caseno);
		Double empIncome = income.getEmpIncome();
		Double propertyIncome = income.getPropertyIncome();
		
		//
		if(planName.equalsIgnoreCase("SNAP")) {
			if(empIncome<=300) {
				ed.setPlanStatus("approved");
				ed.setBenfitAmount(200.0);
			}else {
				ed.setPlanStatus("denied");
				ed.setDenialReason("High Income");
			}
		}
		else if(planName.equalsIgnoreCase("CCAP")) {
			boolean kidsCount = false;
			boolean kidsAge = true;
			//get the children Objects
			List<DcChildrenEntity> child = childRepo.findByCaseNumber(caseno);
			if(child!=null) {
				kidsCount=true;
			}
			
			for(DcChildrenEntity chil:child) {
				Integer childAge = Period.between(chil.getDob(), LocalDate.now()).getYears();
				if(childAge>16) {
					kidsAge =false;
					break;
				}
			}
			
			if(kidsCount && kidsAge && empIncome<=300) {
				ed.setPlanStatus("approved");
				ed.setBenfitAmount(300.0);
			}else {
				ed.setDenialReason("CCAP rules are not satisfied");
				ed.setPlanStatus("denied");
			}
		}
		else if(planName.equalsIgnoreCase("MEDCARE")) {
			if(citizenAge<=65) {
				ed.setPlanStatus("approved");
				ed.setBenfitAmount(350.0);
			}else {
				ed.setDenialReason("MEDCARE rules are not satisfied");
				ed.setPlanStatus("denied");
			}
		}
		else if(planName.equalsIgnoreCase("MEDAID")) {
			if(propertyIncome== 0&& empIncome<=300) {
				ed.setPlanStatus("approved");
				ed.setBenfitAmount(200.0);
			}else {
				ed.setDenialReason("MEDAID rules are not satisfied");
				ed.setPlanStatus("denied");
			}
		}
		else if(planName.equalsIgnoreCase("QHP")) {
			if(citizenAge>=1) {
				ed.setPlanStatus("approved");
			}else {
				ed.setDenialReason("QHP rules are not satisfied");
				ed.setPlanStatus("denied");
			}
		}
		
		if(ed.getPlanStatus().equalsIgnoreCase("approved")) {
			ed.setPlanStartDate(LocalDate.now());
			ed.setPlanEndDate(LocalDate.now().plusYears(5));
		}
		
		return ed;
		
	}

}
