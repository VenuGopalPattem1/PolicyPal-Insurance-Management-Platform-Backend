package com.nt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CITIZEN_APPLICATION")
@NoArgsConstructor
@AllArgsConstructor
public class CitizenAppRegistartionEntity {
	@Id
	@SequenceGenerator(name = "gen1",sequenceName = "citizen_app_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer appId;
	@Column(length = 30)
	private String fullName;
	@Column(length = 30)
	private String email;
	@Column(length = 10)
	private String gender;
	private Long phoneNumber;
	private Long ssn;
	private String stateName;
	private LocalDate dob;
	@Column(length = 30)
	private String createdBy;
	private String updatedBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updateTime;
}
