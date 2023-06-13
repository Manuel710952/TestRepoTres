package com.maesquiv.servlet.servletprueba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.maesquiv.dto.ConectionFTPPojo;
import com.maesquiv.dto.PagosDTO;
import com.maesquiv.task.service.readfiles.ReadFileFTPService;
import com.maesquiv.task.service.readfiles.ReadFilesFTPImpl;

public class FTPConsole {
	
	private static boolean createDirectory(FTPClient ftpClient, String path) throws IOException {
		boolean creado = ftpClient.makeDirectory(path);
		if(creado)
			System.out.println("directorio creado");
		return creado;
	}
	
	public static void main(String[] args) {
		
		/*ReadFileFTPService serviceFTP;
		final String PATH_FILE_FTP_JOB = "/testDss/TestFileTres.txt";
		
		serviceFTP = new ReadFileFTPService(
				new ReadFilesFTPImpl(),
				new ConectionFTPPojo("ftp.dlptest.com", 21, "dlpuser", "rNrKYTX9g7z3RgJRmxWuGHbeu"),
				PATH_FILE_FTP_JOB);
		List<PagosDTO> pagos = serviceFTP.getPagosAplicados();
		System.out.println("los pagos son::" + pagos.toString());*/
		
		
		String server = "ftp.dlptest.com";
		int port = 21;
		String userName = "dlpuser";
		String password = "rNrKYTX9g7z3RgJRmxWuGHbeu";
		String filePath = "/testDss/TestFileTres.txt";
		
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			ftpClient.login(userName, password);
			
			/*InputStream ip = ftpClient.retrieveFileStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(ip));
			
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			br.close();
			
			
			FTPFile[] directorios = ftpClient.listDirectories();
			Arrays.stream(directorios).forEach(d -> {
				if(d.isDirectory()) {
					System.out.println(d.getName());
				}
			});*/
			
			boolean creado = createDirectory(ftpClient, "/testDss");
			File localFile = new File("C:/FilesJobs/pagosAplicados.txt");
			FileInputStream inp = new FileInputStream(localFile);
			
			boolean upload = ftpClient.storeFile("/testDss/TestFileTres.txt", inp);
			if(upload)
				System.out.println("Archivo creado");
			else
				System.out.println("No se pudo crear");
			
			inp.close();
			
			ftpClient.logout();
			ftpClient.disconnect();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
