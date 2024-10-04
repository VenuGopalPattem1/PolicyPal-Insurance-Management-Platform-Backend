package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.CaseWorkersInputs;
import com.nt.binding.PlanDataInputs;
import com.nt.entity.CaseWorkersEntity;
import com.nt.entity.PlanEntity;
import com.nt.repo.ICaseWorkersAccountRepo;
import com.nt.repo.IPlanDataRepo;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IPlanDataRepo repo;
	@Autowired
	private ICaseWorkersAccountRepo caseRepo;
	
	@Override
	public String savePlan(PlanDataInputs inputs) {
		PlanEntity entity = new PlanEntity();
		BeanUtils.copyProperties(inputs, entity);
		Integer id = repo.save(entity).getPlanId();
		return "Plan Details Are save with Id value "+id;
	}

	@Override
	public String updatePlanDetals(PlanDataInputs inputs) {
		Optional<PlanEntity> entity = repo.findById(inputs.getPlanId());
		if(entity.isPresent()) {
			PlanEntity main = entity.get();
			BeanUtils.copyProperties(inputs, main);
			repo.save(main);
			return "Object found and updated sucessfully";
		}
		return "Object is not found with the give values";
	}

	@Override
	public String changePlanStatus(Integer planId,String status) {
		Optional<PlanEntity> entity = repo.findById(planId);
		if(entity.isPresent()) {
			PlanEntity main = entity.get();
			main.setActiveSw(status);
			repo.save(main);
			return "plan is found and plan status is updated sucessfully";
		}
		return "plan object is not found with given id value "+planId;
	}

	@Override
	public List<PlanDataInputs> getAllPlansData() {
		List<PlanEntity> list =  repo.findAll();
		List<PlanDataInputs> data = new ArrayList<PlanDataInputs>();
		list.forEach(info->{
			PlanDataInputs inputs = new PlanDataInputs();
			BeanUtils.copyProperties(info, inputs);
			data.add(inputs);
		});
		return data;
	}

	@Override
	public String deletePlanById(Integer id) {
		Optional<PlanEntity> entity = repo.findById(id);
		if(entity.isPresent()) {
			repo.deleteById(id);
			return "plan is found and plan is deleted sucessfully";
		}
		return "plan object is not found with given id value "+id;
	}

	@Override
	public PlanDataInputs getPlanBYId(Integer planId) {
		Optional<PlanEntity> entity = repo.findById(planId);
		if(entity.isPresent()) {
			PlanEntity main = entity.get();
			PlanDataInputs inputs = new PlanDataInputs();
			BeanUtils.copyProperties(main, inputs);
			return inputs;
		}
		throw new IllegalArgumentException("plan object is not found with given id value "+planId);
	}

	
	//caseworkers related imlementation methods curd operations
	
	
	@Override
	public String saveCW(CaseWorkersInputs inputs) {
		CaseWorkersEntity entity = new CaseWorkersEntity();
		BeanUtils.copyProperties(inputs, entity);
		Integer id =caseRepo.save(entity).getAccountId();
		return "CW Details Are save with Id value "+id;
	}

	@Override
	public String updateCWDetails(CaseWorkersInputs inputs) {
		Optional<CaseWorkersEntity> entity = caseRepo.findById(inputs.getAccountId());
		if(entity.isPresent()) {
			CaseWorkersEntity main = entity.get();
			BeanUtils.copyProperties(inputs, main);
			caseRepo.save(main);
			return "CW Object found and updated sucessfully";
		}
		return " CW Object is not found with the give values";
	}

	@Override
	public String changeCWStatus(Integer planId, String status) {
		Optional<CaseWorkersEntity> entity = caseRepo.findById(planId);
		if(entity.isPresent()) {
			CaseWorkersEntity main = entity.get();
			main.setActiveSw(status);
			caseRepo.save(main);
			return "CW is found and CW status is updated sucessfully";
		}
		return "CW object is not found with given id value "+planId;
	}

	@Override
	public List<CaseWorkersInputs> getAllCWData() {
		List<CaseWorkersEntity> list =  caseRepo.findAll();
		List<CaseWorkersInputs> data = new ArrayList<CaseWorkersInputs>();
		list.forEach(info->{
			CaseWorkersInputs inputs = new CaseWorkersInputs();
			BeanUtils.copyProperties(info, inputs);
			data.add(inputs);
		});
		return data;
	}

	@Override
	public String deleteCW(Integer id) {
		Optional<CaseWorkersEntity> entity = caseRepo.findById(id);
		if(entity.isPresent()) {
			caseRepo.deleteById(id);
			return "CW object is found and CW is deleted sucessfully";
		}
		return "CW object is not found with given id value "+id;
	}

	@Override
	public CaseWorkersInputs getCWById(Integer planId) {
		Optional<CaseWorkersEntity> entity = caseRepo.findById(planId);
		if(entity.isPresent()) {
			CaseWorkersEntity main = entity.get();
			CaseWorkersInputs inputs = new CaseWorkersInputs();
			BeanUtils.copyProperties(main, inputs);
			return inputs;
		}
		throw new IllegalArgumentException("Cw object is not found with given id value "+planId);
	}

}
