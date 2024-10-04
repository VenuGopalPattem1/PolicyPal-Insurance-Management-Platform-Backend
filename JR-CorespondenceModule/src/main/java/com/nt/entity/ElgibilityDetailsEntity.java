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

@Entity
@Table(name = "ELGIBILITY_DETERMINATION")
@Data
public class ElgibilityDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edId;
	private Integer caseNo;
	@Column(length = 30)
	private String holderName;
	@Column(length = 10)
	private Long holderSsn;
	@Column(length = 30)
	private String planName;
	@Column(length = 30)
	private String planStatus;
	private Double benfitAmount;
	@Column(length = 50)
	private String denialReason;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private String createdBy;
	private String updatedBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updateTime;
	
}
