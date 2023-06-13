package com.maesquiv.task.service.runnerjob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;

public class RunnerJobService {
	
	private static final Logger LOG = LogManager.getLogger(RunnerJobService.class);
	
	private IRunnerJob runnerJob;
	
	public RunnerJobService(IRunnerJob runnerJob) {
		LOG.info("constructor::RunnerJobService::");
		this.runnerJob = runnerJob;
	}
	
	public void  initTask(Scheduler scheduler) {
		LOG.info("RunnerJobService::initTask()::");
		this.runnerJob.execute(scheduler);
	}
}
