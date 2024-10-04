package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DC_CASES")
public class DcCasesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer caseNo;
//	private Integer caseNumber;
	private Integer appId;
	private Integer planId;
}
