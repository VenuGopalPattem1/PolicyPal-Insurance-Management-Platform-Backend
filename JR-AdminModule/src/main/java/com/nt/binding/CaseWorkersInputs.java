package com.nt.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CaseWorkersInputs {
	private Integer accountId;
	private String name;
	private String email;
	private String pwd;
	private String gender;
	private Integer ssn;
	private LocalDate dob;
	private String activeSw;
}
