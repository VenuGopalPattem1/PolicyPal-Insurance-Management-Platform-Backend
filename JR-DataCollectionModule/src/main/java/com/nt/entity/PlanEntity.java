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
@Table(name = "PLAN_DETALS")
public class PlanEntity {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer planId;
	@Column(length = 30)
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	@Column(length = 50)
	private String description;
	private String activeSw;
	private String createdBy;
	private String updatedBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updateTime;
}
