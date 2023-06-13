package com.maesquiv.task.service.readfiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.maesquiv.dto.ConectionFTPPojo;
import com.maesquiv.dto.PagosDTO;
import com.maesquiv.task.constants.ConstantsFileJobPagos;
import com.maesquiv.task.util.MapperUtil;

public class ReadFilesFTPImpl implements IReadFilesFormFTP<PagosDTO> {
	
	private static final Logger LOG = LogManager.getLogger(ReadFilesFTPImpl.class);

	@Override
	public List<PagosDTO> readTxtFile(FTPClient ftpClient, String pathFile) {
		List<PagosDTO> pagosAplicados = null;
		BufferedReader br = null;
		InputStream ip = null;
		String line = null;
		boolean isFirstLine = true;
		
		System.out.println("Entro a la lectura::1::" + pathFile);
		System.out.println("Entro a la lectura::2::" + (ftpClient != null));
		try {
			ip = ftpClient.retrieveFileStream(pathFile);
			br = new BufferedReader(new InputStreamReader(ip));
			
			pagosAplicados = new ArrayList<>();
			while((line = br.readLine()) != null) {
				String[] columnas = line.split(ConstantsFileJobPagos.SEPARADOR);
				if(!isFirstLine && columnas.length == ConstantsFileJobPagos.LIMIT_NUMBER_COLUMS) {
					PagosDTO pago = MapperUtil.stringToPagos(columnas);
					pagosAplicados.add(pago);
				}
				
				if(isFirstLine) {
					isFirstLine = !isFirstLine;
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			LOG.error("ERROR::readTxtWithPath::" + e.getMessage());
		}
		finally {
			if(br != null) {
				try {
					br.close();
					ip.close();
				} catch (IOException e) {
					LOG.error("ERROR::readTxtWithPath::" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return pagosAplicados;
	}

	@Override
	public FTPClient getConnection(ConectionFTPPojo credentials) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(credentials.getServerName(), credentials.getPort());
			ftpClient.login(credentials.getUserName(), credentials.getPassword());
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al conectarse a ftp server");
		}
		return ftpClient;
	}

	@Override
	public void closeConnection(FTPClient ftpClient) {
		try {
			ftpClient.logout();
			ftpClient.disconnect();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al cerrar ftp server");
		}
	}

}
