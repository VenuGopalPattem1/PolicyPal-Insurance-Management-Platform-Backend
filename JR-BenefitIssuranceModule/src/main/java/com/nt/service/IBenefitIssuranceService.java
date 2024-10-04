package com.nt.service;

import org.springframework.batch.core.JobExecution;

public interface IBenefitIssuranceService {
	public JobExecution sendBenifitAmount() throws Exception;
}
