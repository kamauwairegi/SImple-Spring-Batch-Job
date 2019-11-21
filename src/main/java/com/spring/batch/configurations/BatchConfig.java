package com.spring.batch.configurations;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.batch.services.UserService;
import com.spring.batch.tasks.TaskOne;
import com.spring.batch.tasks.TaskTwo;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;
	
	@Autowired
	UserService service;

	@Bean
	public Step stepOne() {
		return steps.get("stepOne").tasklet(new TaskOne(service)).build();
	}

	@Bean
	public Step stepTwo() {
		return steps.get("stepTwo").tasklet(new TaskTwo(service)).build();
	}

	@Bean
	public Job simpleJob() {
		return jobs.get("simpleJob").incrementer(new RunIdIncrementer()).start(stepOne()).next(stepTwo()).build();
	}
}
