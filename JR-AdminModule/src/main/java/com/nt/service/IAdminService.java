package com.nt.service;

import java.util.List;

import com.nt.binding.CaseWorkersInputs;
import com.nt.binding.PlanDataInputs;

public interface IAdminService {
	//plan related curd operations
	public String savePlan(PlanDataInputs inputs);
	public String updatePlanDetals(PlanDataInputs inputs);
	public String changePlanStatus(Integer planId,String status);
	public List<PlanDataInputs> getAllPlansData();
	public String deletePlanById(Integer id);
	public PlanDataInputs getPlanBYId(Integer planId);
	
	//caseworkers related curd operations
	public String saveCW(CaseWorkersInputs inputs);
	public String updateCWDetails(CaseWorkersInputs inputs);
	public String changeCWStatus(Integer planId,String status);
	public List<CaseWorkersInputs> getAllCWData();
	public String deleteCW(Integer id);
	public CaseWorkersInputs getCWById(Integer planId);
	
}
