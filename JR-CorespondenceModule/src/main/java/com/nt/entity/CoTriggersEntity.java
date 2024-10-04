package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CO_TRIGGERS")
@Data
public class CoTriggersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coId;
	private Integer caseNo;
	@Lob
	@Column(length = 1000)
	private byte[] coNoticePdf;
	private String triggerStatus="pending";
}
