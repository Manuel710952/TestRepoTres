package com.maesquiv.servlet.servletprueba;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.maesquiv.task.service.runnerjob.IRunnerJob;
import com.maesquiv.task.service.runnerjob.RunnerJobImpl;
import com.maesquiv.task.service.runnerjob.RunnerJobService;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RunnerWebListener implements ServletContextListener {
	
	private static final Logger LOG = LogManager.getLogger(RunnerWebListener.class);
	private RunnerJobService serviceJob;
	private Scheduler scheduler;
	Properties props = new Properties();
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LOG.info("ENTRO::contextInitialized::TestQuartz::init");
		try {
			this.scheduler = new StdSchedulerFactory().getScheduler();
			IRunnerJob runnerJobImpl = new RunnerJobImpl();
			this.serviceJob = new RunnerJobService(runnerJobImpl);
			LOG.info("ENTRO::doGet::TestQuartz");
			serviceJob.initTask(this.scheduler);
			this.printPropertieEnviroment();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
	
	public void printPropertieEnviroment() {
		try {
			InputStream input = RunnerWebListener.class.getClassLoader().getResourceAsStream("config.properties");
			this.props.load(input);
			LOG.info("ENTRO::doGet::printPropertieEnviroment::" + this.props.getProperty("ftpserver.port"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
 
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	LOG.info("destruyo::contextDestroyed");
		try {
			this.scheduler.shutdown();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
 
}
