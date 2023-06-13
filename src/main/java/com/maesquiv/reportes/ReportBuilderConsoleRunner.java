package com.maesquiv.reportes;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.maesquiv.dto.PagosDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportBuilderConsoleRunner {
	public static void main(String[] args) {
		try {
			System.out.println("iniciando la generacion del reporte");
			InputStream reportStream = ReportBuilderConsoleRunner.class.getResourceAsStream("/reporte.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
			List<PagosDTO> i = Arrays.asList(new PagosDTO(121212, "primer poago", 1), new PagosDTO(1232, "segundo poago", 2), new PagosDTO(45467, "tercer poago", 1));
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(i);
			System.out.println("listaDtaSource::" + dataSource.toString());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/reportes/reporte.pdf");
			System.out.println("El reporte se ha generado con exito");
		} catch (JRException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
