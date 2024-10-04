package com.nt.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseWorkersOutput {
	private Integer accountId;
	private String name;
	private String email;
	private String pwd;
	private String gender;
	private Integer ssn;
	private LocalDate dob;
}
