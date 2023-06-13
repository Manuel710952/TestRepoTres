package com.maesquiv.task;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.maesquiv.dto.ConectionFTPPojo;
import com.maesquiv.dto.PagosDTO;
import com.maesquiv.task.service.readfiles.IReadFiles;
import com.maesquiv.task.service.readfiles.ReadFileFTPService;
import com.maesquiv.task.service.readfiles.ReadFileService;
import com.maesquiv.task.service.readfiles.ReadFilesFTPImpl;
import com.maesquiv.task.service.readfiles.ReadFilesImpl;

public class JobAplicarPagos implements Job {
	
	private static final Logger LOG = LogManager.getLogger(JobAplicarPagos.class);
	
	private ReadFileService serviceFiles;
	private final String PATH_FILE_JOB = "C:/FilesJobs/pagosAplicados.txt";
	
	private ReadFileFTPService serviceFTP;
	private final String PATH_FILE_FTP_JOB = "/testDss/TestFileTres.txt";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//this.serviceFiles = new ReadFileService(new ReadFilesImpl());
		//List<PagosDTO> pagos =  this.serviceFiles.readFileTxt(PATH_FILE_JOB);
		this.serviceFTP = new ReadFileFTPService(
				new ReadFilesFTPImpl(),
				new ConectionFTPPojo("ftp.dlptest.com", 21, "dlpuser", "rNrKYTX9g7z3RgJRmxWuGHbeu"),
				PATH_FILE_FTP_JOB);
		List<PagosDTO> pagos = this.serviceFTP.getPagosAplicados();
		LOG.info("execute::Ejecuto tarea:::EXITOSO..................." + pagos.toString());
	}

}
