package com.spring.batch.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.spring.batch.services.UserService;

public class TaskOne implements Tasklet {

	private UserService service;

	public TaskOne(UserService userService) {
		this.service = userService;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		service.insertUsers();
		return RepeatStatus.FINISHED;
	}

}
