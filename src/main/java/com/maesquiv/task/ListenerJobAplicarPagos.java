package com.maesquiv.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class ListenerJobAplicarPagos implements JobListener {
	
	private static final Logger LOG = LogManager.getLogger(ListenerJobAplicarPagos.class);
	
	private int numberRetries;
	private Scheduler scheduler;
	
	private static final int MAX_RETRIES = 3;
	private static final String NAME_JOB = "ListenerJobAplicarPagos";
	
	
	public ListenerJobAplicarPagos(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public String getName() {
		return NAME_JOB;
	}
	
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		if(jobException != null && this.numberRetries < MAX_RETRIES) {
			this.numberRetries ++;
			JobDetail jobDetail = context.getJobDetail();
			Trigger trigger = context.getTrigger();
			try {
				scheduler.deleteJob(jobDetail.getKey());
				scheduler.unscheduleJob(trigger.getKey());
				scheduler.scheduleJob(jobDetail, trigger);
			}
			catch (SchedulerException e) {
				e.printStackTrace();
				LOG.error("ERROR::jobWasExecuted::Ocurrio un error al intentar hacer el reintento .....");
			}
		}
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {}
}
