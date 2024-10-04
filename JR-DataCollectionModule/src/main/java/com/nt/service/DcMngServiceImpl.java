package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.ChildrenInputs;
import com.nt.binding.CitizenAppRegistationIntput;
import com.nt.binding.DcSummaryReports;
import com.nt.binding.EducationalInputs;
import com.nt.binding.IncomeInputs;
import com.nt.binding.PlanSelectionInputs;
import com.nt.entity.CitizenAppRegistartionEntity;
import com.nt.entity.DcCasesEntity;
import com.nt.entity.DcChildrenEntity;
import com.nt.entity.DcEducationEntity;
import com.nt.entity.DcIncomeEntity;
import com.nt.entity.PlanEntity;
import com.nt.exeception.InvalidCaseNoException;
import com.nt.repo.ICitizenAppRepo;
import com.nt.repo.IDcCaseRepo;
import com.nt.repo.IDcChildrenRepo;
import com.nt.repo.IDcEducatioRepo;
import com.nt.repo.IDcIncomeRepo;
import com.nt.repo.IPlanDetalsRepo;

@Service
public class DcMngServiceImpl implements IDcMngService{
	@Autowired
	private ICitizenAppRepo citizenRepo;
	@Autowired
	private IDcCaseRepo caseRepo;
	@Autowired
	private IPlanDetalsRepo planRepo;
	@Autowired
	private IDcChildrenRepo childRepo;
	@Autowired
	private IDcEducatioRepo eduRepo;
	@Autowired
	private IDcIncomeRepo incomeRepo;
//	@Autowired
//	private IDcMngService dcRepo;
//	
	@Override
	public Integer generateCaseNumber(Integer appId) {
		Optional<CitizenAppRegistartionEntity> citizen = citizenRepo.findById(appId);
		if(citizen.isPresent()) {
			DcCasesEntity cases = new DcCasesEntity();
			cases.setAppId(appId);
			return caseRepo.save(cases).getCaseNo();
		}
		throw new InvalidCaseNoException("Invalid Application Registration Id ");
	}

	@Override
	public List<String> getAllPlanNames() {
		List<PlanEntity> plans = planRepo.findAll();
		List<String> planNames = plans.stream().map(name->name.getPlanName()).toList();
		return planNames;
	}

	@Override
	public Integer savePlanDetails(PlanSelectionInputs inputs) {
		Optional<DcCasesEntity> caseEntity = caseRepo.findById(inputs.getCaseNo());
		if(caseEntity.isPresent()) {
			DcCasesEntity cases = caseEntity.get();
			cases.setPlanId(inputs.getPlanId());
			return caseRepo.save(cases).getCaseNo();
		}
		throw new InvalidCaseNoException("Invalid Case Number ");
	}

	@Override
	public Integer saveChildrensDetails(List<ChildrenInputs> inputs) {
		inputs.forEach(child->{
			DcChildrenEntity children = new DcChildrenEntity();
			BeanUtils.copyProperties(child, children);
			childRepo.save(children);
		});
		return inputs.get(0).getCaseNumber();
	}

	@Override
	public Integer saveIncomeDetails(IncomeInputs inputs) {
		DcIncomeEntity income = new DcIncomeEntity();
		BeanUtils.copyProperties(inputs, income);
		return incomeRepo.save(income).getCaseNumber();
	}

	@Override
	public Integer saveEducationDetails(EducationalInputs inputs) {
		DcEducationEntity edu = new DcEducationEntity();
		BeanUtils.copyProperties(inputs, edu);
		return eduRepo.save(edu).getCaseNumber();
	}

	@Override
	public DcSummaryReports getSummaryReport(Integer caseNo) {
		// get different types of objects that is needed for the summary report
		List<DcChildrenEntity> childEntity = childRepo.findByCaseNumber(caseNo);
		DcEducationEntity eduEntity = eduRepo.findByCaseNumber(caseNo);
		DcIncomeEntity incomeEntity = incomeRepo.findByCaseNumber(caseNo);
		Optional<DcCasesEntity> cseEntity = caseRepo.findById(caseNo);
		
		//get planName and AppId
		String planName = null;
		Integer appId = null;
		if(cseEntity.isPresent()) {
			DcCasesEntity cas = cseEntity.get();
			//get the app id and plan id with the help of the case Entity
			appId = cas.getAppId();
			int planId = cas.getPlanId();
			//with the help of the plan id we will get the plan Name and set the plan name to the global variables
			Optional<PlanEntity> plan = planRepo.findById(planId);
			if(plan.isPresent()) {
				planName = plan.get().getPlanName();
			}
		}
		else {
			throw new InvalidCaseNoException("Invalid Case Number ");
		}
		
		//copying the entity class Objects to the binding class Objects
		//copying the children entity to the corresponding binding class
		List<ChildrenInputs> listChild = new ArrayList<ChildrenInputs>();
		childEntity.forEach(child->{
			ChildrenInputs children = new ChildrenInputs();
			BeanUtils.copyProperties(child, children);
			listChild.add(children);
		});
		
		//copying the education entity to the corresponding binding class
		EducationalInputs edu = new EducationalInputs();
		BeanUtils.copyProperties(eduEntity, edu);
		
		//copying the income entity to the corresponding binding class
		IncomeInputs income = new IncomeInputs();
		BeanUtils.copyProperties(incomeEntity, income);
		
		//getting the citizenapp entity object and copying its records to corresponding entity classses
		Optional<CitizenAppRegistartionEntity> citizenEntity = citizenRepo.findById(appId);
		CitizenAppRegistartionEntity citizen = null;
		if(citizenEntity.isPresent()) {
			citizen = citizenEntity.get();
		}
		CitizenAppRegistationIntput citizenInputs = new CitizenAppRegistationIntput();
		BeanUtils.copyProperties(citizen, citizenInputs);
		
		//create summary object add the object details into the summary object
		DcSummaryReports summary = new DcSummaryReports();
		summary.setPlanName(planName);
		summary.setChildrenDetails(listChild);
		summary.setCitizeDetails(citizenInputs);
		summary.setEducationDetails(edu);
		summary.setIncomeDetails(income);
		
		return summary;
	}

}
