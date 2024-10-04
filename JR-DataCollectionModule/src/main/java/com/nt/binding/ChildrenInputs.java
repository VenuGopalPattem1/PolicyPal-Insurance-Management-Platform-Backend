package com.nt.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class ChildrenInputs {
	private Integer childrenId;
	private Integer caseNumber;
	private Integer ssn;
	private LocalDate dob;
}
