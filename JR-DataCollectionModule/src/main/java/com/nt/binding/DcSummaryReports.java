package com.nt.binding;

import java.util.List;

import lombok.Data;

@Data
public class DcSummaryReports {
	private String planName;
	private CitizenAppRegistationIntput citizeDetails;
	private IncomeInputs incomeDetails;
	private EducationalInputs educationDetails;
	private List<ChildrenInputs> childrenDetails;
}
