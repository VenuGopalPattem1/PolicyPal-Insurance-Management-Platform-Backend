package com.nt.config;


import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import com.nt.binding.ElgibilityDetailsOutputs;
import com.nt.entity.ElgibilityDetailsEntity;
import com.nt.processor.BIModuleItemProcessor;
import com.nt.repo.IElgibilityDetailsRepo;

@Configuration
public class BatchConfig {

	@Autowired
	private IElgibilityDetailsRepo repo;
	@Autowired
	private BIModuleItemProcessor process;
	
	@Bean("reader")
	public RepositoryItemReader<ElgibilityDetailsEntity> createReader(){
		return new RepositoryItemReaderBuilder<ElgibilityDetailsEntity>()
				.name("repo-reader")
				.repository(repo)
				.methodName("findAll")
				.sorts(Map.of("caseNo",Sort.Direction.ASC))
				.build();
				
	}
	
	@Bean("writer")
	public FlatFileItemWriter<ElgibilityDetailsOutputs> createWriter(){
		return new FlatFileItemWriterBuilder<ElgibilityDetailsOutputs>()
				.name("file-writer")
				.resource(new FileSystemResource("beneficieries_list.csv"))
				.lineSeparator("\r\n")
				.delimited().delimiter(",")
				.names("caseNo","holderName","holderSsn","planName","planStatus","benfitAmount")
				.build();
	}
	
	@Bean("step")
	public Step createStep(JobRepository jrepo,PlatformTransactionManager ptm) {
		return new StepBuilder("step",jrepo)
				.<ElgibilityDetailsEntity,ElgibilityDetailsOutputs>chunk(3,ptm)
				.reader(createReader())
				.processor(process)
				.writer(createWriter())
				.build();
	}
	
	@Bean("job")
	public Job createJob(JobRepository jrepo,Step s) {
		return new JobBuilder("job",jrepo)
				.incrementer(new RunIdIncrementer())
				.start(s)
				.build();
	}
}
