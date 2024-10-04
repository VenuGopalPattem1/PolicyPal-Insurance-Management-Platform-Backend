package com.nt.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlanDataInputs {
	private Integer planId;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private String description;
	private String activeSw;
}
