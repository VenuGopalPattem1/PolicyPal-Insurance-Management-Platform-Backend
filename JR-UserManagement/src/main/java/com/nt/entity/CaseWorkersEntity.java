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
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CASE_WORKERS_ACCOUNT")
public class CaseWorkersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;
	@Column(length = 30)
	private String name;
	@Column(length = 30)
	private String email;
	@Column(length = 20)
	private String pwd;
	@Column(length = 10)
	private String gender;
	@Column(length = 30)
	private Integer ssn;
	private String activeSw;
	private LocalDate dob;
	private String createdBy;
	private String updatedBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updateTime;
}
