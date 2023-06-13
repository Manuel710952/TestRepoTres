package com.maesquiv.task.service.readfiles;

import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import com.maesquiv.dto.ConectionFTPPojo;

public interface IReadFilesFormFTP<T> {
	List<T> readTxtFile(FTPClient ftpClient, String pathFile);
	FTPClient getConnection(ConectionFTPPojo credentials);
	void closeConnection(FTPClient ftpClient);
}
