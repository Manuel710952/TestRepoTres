package com.maesquiv.servlet.servletprueba;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.maesquiv.task.JobAplicarPagos;
import com.maesquiv.task.ListenerJobAplicarPagos;
import com.maesquiv.task.constants.ConstantsTaskDetails;
import com.maesquiv.task.service.runnerjob.IRunnerJob;
import com.maesquiv.task.service.runnerjob.RunnerJobImpl;
import com.maesquiv.task.service.runnerjob.RunnerJobService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/TestQuartz")
//public class RunnerJobServlet extends HttpServlet {
public class RunnerJobServlet {
	
	private static final Logger LOG = LogManager.getLogger(RunnerJobServlet.class);
	private RunnerJobService serviceJob;
	private Scheduler scheduler;
	
	public void init() {
		LOG.info("ENTRO::doGet::TestQuartz::init");
		try {
			this.scheduler = new StdSchedulerFactory().getScheduler();
			IRunnerJob runnerJobImpl = new RunnerJobImpl();
			this.serviceJob = new RunnerJobService(runnerJobImpl);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("ENTRO::doGet::TestQuartz");
		PrintWriter out = response.getWriter();
		serviceJob.initTask(this.scheduler);
		out.println("<h1>Ejecutando task .....</h1>");
	}
	
	public void destroy() {
		LOG.info("destruyo::SERVLET");
		try {
			this.scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
