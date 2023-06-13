package com.maesquiv.task.service.readfiles;

import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.maesquiv.dto.ConectionFTPPojo;
import com.maesquiv.dto.PagosDTO;

public class ReadFileFTPService {
	
	private static final Logger LOG = LogManager.getLogger(ReadFileFTPService.class);
	
	private IReadFilesFormFTP<PagosDTO> implService;
	private ConectionFTPPojo credentialsConn;
	private FTPClient ftpClient;
	private String pathFileJob;
	
	public ReadFileFTPService(IReadFilesFormFTP<PagosDTO> implService, ConectionFTPPojo credentialsConn, String pathFileJob) {
		this.implService = implService;
		this.credentialsConn = credentialsConn;
		this.pathFileJob = pathFileJob;
		
		this.ftpClient = this.implService.getConnection(this.credentialsConn);
	}
	
	
	public List<PagosDTO> getPagosAplicados() {
		List<PagosDTO> pagos = this.implService.readTxtFile(this.ftpClient, getPathFileJob());
		return pagos;
	}


	public ConectionFTPPojo getCredentialsConn() {
		return credentialsConn;
	}


	public void setCredentialsConn(ConectionFTPPojo credentialsConn) {
		this.credentialsConn = credentialsConn;
	}


	public String getPathFileJob() {
		return pathFileJob;
	}


	public void setPathFileJob(String pathFileJob) {
		this.pathFileJob = pathFileJob;
	}
}
