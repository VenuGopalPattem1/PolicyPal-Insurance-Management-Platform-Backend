package com.nt.entity;

import java.time.LocalDate;

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
@Table(name = "DC_CHILD")
public class DcChildrenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer childrenId;
	private Integer caseNumber;
	private Integer ssn;
	private LocalDate dob;

}
