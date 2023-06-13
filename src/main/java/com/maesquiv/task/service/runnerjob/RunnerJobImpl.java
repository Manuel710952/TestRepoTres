package com.maesquiv.task.service.runnerjob;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.maesquiv.task.JobAplicarPagos;
import com.maesquiv.task.ListenerJobAplicarPagos;
import com.maesquiv.task.constants.ConstantsTaskDetails;

public class RunnerJobImpl implements IRunnerJob {
	
	private static final Logger LOG = LogManager.getLogger(RunnerJobImpl.class);

	@Override
	public void execute(Scheduler scheduler) {
		LOG.info("RunnerJobImpl::execute()");
		LocalTime horaActual = LocalTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh::mm:ss");
		String horaFormateada = horaActual.format(formato);
		LOG.info("RunnerJobImpl::execute()::La hor aactual es::" + horaFormateada.toString());
		
		JobDetail job = JobBuilder.newJob(JobAplicarPagos.class)
				.withIdentity(ConstantsTaskDetails.NAME_JOB, ConstantsTaskDetails.NAME_GROUP_JOB)
				.build();
		
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(ConstantsTaskDetails.NAME_TRIGGER, ConstantsTaskDetails.NAME_GROUP_JOB)
				.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(13, 6))
				.build();
		
		/*Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("miTrigger", "grupo1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(3)
						.withRepeatCount(2)
						.repeatForever())
				.build();*/
		
		try {
			//Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.getListenerManager().addJobListener(new ListenerJobAplicarPagos(scheduler));
			scheduler.scheduleJob(job,trigger);
			scheduler.start();
			LOG.info("RunnerJobImpl::TERMINO LA TAREA");
		}
		catch (Exception e) {
			LOG.error("ERROR::executeJob::Error al programar tarea .....");
			e.printStackTrace();
			throw new RuntimeException("ERROR::executeJob::Error al programar tarea .....");
		}
	}

}
