package com.nt.service;

import java.util.Date;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenefitIssuranceServiceImpl implements IBenefitIssuranceService {
	
	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;
	@Override
	public JobExecution sendBenifitAmount() throws Exception {
		//prepare job parameters Object
		JobParameter<Date> param = new JobParameter<Date>(new Date(), Date.class);
		Map<String,JobParameter<?>> map = Map.of("param1",param);
		JobParameters parameters = new JobParameters(map);
		//call the run(-) method
		JobExecution ex = launcher.run(job, parameters);
		System.out.println("Job Excetion status ::"+ex.getExitStatus());
		return ex;
	}

}
