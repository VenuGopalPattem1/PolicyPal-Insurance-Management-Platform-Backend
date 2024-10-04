package com.nt.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ElgibilityDetailsOutputs {
	private Integer caseNo;
	private String holderName;
	private Long holderSsn;
	private String planName;
	private String planStatus;
	private Double benfitAmount;
	private String denialReason;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
}
