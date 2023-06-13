package com.maesquiv.task.service.readfiles;

import java.util.List;

import com.maesquiv.dto.PagosDTO;

public class ReadFileService {
	private IReadFiles readServices;
	
	public ReadFileService(IReadFiles readServices) {
		this.readServices = readServices;
	}
	
	public List<PagosDTO> readFileTxt(String path) {
		return this.readServices.readTxtWithPath(path);
	}
	
	public List<PagosDTO> readFileCsv(String path) {
		return this.readServices.readCsvWithPath(path);
	}
}
