package com.nt.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenAppRegistationIntput {
	private String fullName;
	private String email;
	private String gender;
	private Long phoneNumber;
	private Long ssn;
	private LocalDate dob;
}
