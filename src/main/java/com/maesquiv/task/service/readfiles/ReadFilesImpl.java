package com.maesquiv.task.service.readfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.maesquiv.dto.PagosDTO;
import com.maesquiv.task.constants.ConstantsFileJobPagos;
import com.maesquiv.task.util.MapperUtil;
import com.newrelic.api.agent.Trace;

public class ReadFilesImpl implements IReadFiles<PagosDTO> {
	
	private static final Logger LOG = LogManager.getLogger(ReadFilesImpl.class);

	@Override
	@Trace(metricName = "Servicios/ReadFilesImpl/readTxtWithPath")
	public List<PagosDTO> readTxtWithPath(String path) {
		LOG.info("PROCESANDO::readTxtWithPath::");
		List<PagosDTO> pagosAplicados = null;
		BufferedReader br = null;
		String line = null;
		boolean isFirstLine = true;
		try {
			br = new BufferedReader(new FileReader(path));
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
				} catch (IOException e) {
					LOG.error("ERROR::readTxtWithPath::" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return pagosAplicados;
	}
	
	@Override
	public void readTxt() {}

	@Override
	public List<PagosDTO> readCsvWithPath(String path) {
		String csvFile = "C:/FilesJobs/PagosAplicadosCS.csv";
		System.out.println("ENTRO::readCsvWithPath");
		try {
			Reader reader = new FileReader(csvFile);
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			csvParser.forEach(c -> {
				String columnaUno = c.get(0);
				System.out.println("El valor de la columna es::" + columnaUno);
			});
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
