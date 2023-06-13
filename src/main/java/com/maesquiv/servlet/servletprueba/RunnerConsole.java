package com.maesquiv.servlet.servletprueba;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.maesquiv.dto.PagosDTO;
import com.maesquiv.task.service.readfiles.ReadFileService;
import com.maesquiv.task.service.readfiles.ReadFilesImpl;
import com.maesquiv.task.service.runnerjob.IRunnerJob;
import com.maesquiv.task.service.runnerjob.RunnerJobImpl;
import com.maesquiv.task.service.runnerjob.RunnerJobService;

public class RunnerConsole {
	private static final Logger LOG = LogManager.getLogger(RunnerConsole.class);
	public static void main(String[] args) {
		Scheduler scheduler;
		try {
			/*scheduler = new StdSchedulerFactory().getScheduler();
			IRunnerJob runnerJobImpl = new RunnerJobImpl();
			RunnerJobService serviceJob = new RunnerJobService(runnerJobImpl);
			LOG.info("ENTRO::doGet::TestQuartz");
			serviceJob.initTask(scheduler);*/
			
			/*ReadFileService serviceFiles = new ReadFileService(new ReadFilesImpl());
			List<PagosDTO> pagos =  serviceFiles.readFileTxt("C:/FilesJobs/pagosAplicados.txt");
			System.out.println("pagos::" + pagos.toString());*/
			
			ReadFileService serviceFiles = new ReadFileService(new ReadFilesImpl());
			serviceFiles.readFileCsv("");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
