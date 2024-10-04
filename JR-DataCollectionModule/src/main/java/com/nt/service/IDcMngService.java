package com.nt.service;

import java.util.List;

import com.nt.binding.ChildrenInputs;
import com.nt.binding.DcSummaryReports;
import com.nt.binding.EducationalInputs;
import com.nt.binding.IncomeInputs;
import com.nt.binding.PlanSelectionInputs;

public interface IDcMngService {
	public Integer generateCaseNumber(Integer appId);
	public List<String> getAllPlanNames();
	public Integer savePlanDetails(PlanSelectionInputs inputs);
	public Integer saveChildrensDetails(List<ChildrenInputs> inputs);
	public Integer saveIncomeDetails(IncomeInputs inputs);
	public Integer saveEducationDetails(EducationalInputs inputs);
	public DcSummaryReports getSummaryReport(Integer caseNo);
}
