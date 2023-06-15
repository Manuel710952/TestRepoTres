package com.maesquiv.reportes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.renderer.category.WaterfallBarRenderer;

import com.maesquiv.dto.PagosDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;


public class ReportBuilderConsoleRunner {
	public static void main(String[] args) {
		try {
			//System.out.println("iniciando la generacion del reporte");
			InputStream reportStream = ReportBuilderConsoleRunner.class.getResourceAsStream("/reporte.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
			List<PagosDTO> i = Arrays.asList(new PagosDTO(121212, "primer poago", 1), new PagosDTO(1232, "segundo poago", 2), new PagosDTO(45467, "tercer poago", 1));
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(i);
			//System.out.println("listaDtaSource::" + dataSource.toString());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			
			SimplePdfExporterConfiguration exporterConfiguration = new SimplePdfExporterConfiguration();
            exporterConfiguration.setCreatingBatchModeBookmarks(true);
            exporterConfiguration.setMetadataAuthor("Autor del informe");
            exporterConfiguration.setMetadataTitle("Título del informe");
			
            // Exportar el informe a PDF sin marca de agua
            exporter.setConfiguration(exporterConfiguration);
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:/reportes/reporte.pdf"));
            exporter.exportReport();

            // Agregar marca de agua al PDF generado
            addWatermarkToPDF("C:/reportes/reporte.pdf", "C:/reportes/img-marca-agua.jpg");

            System.out.println("Informe generado exitosamente.");
			
			
			//JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/reportes/reporte.pdf");
			//System.out.println("El reporte se ha generado con exito");
		} catch (JRException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void addWatermarkToPDF(String inputPath, String watermarkImagePath) {
        try {
            PdfReader reader = new PdfReader(inputPath);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("C:/reportes/reporteConMarca.pdf"));
            Image watermarkImage = Image.getInstance(watermarkImagePath);

            int totalPages = reader.getNumberOfPages();
            Rectangle pageSize;
            float x, y;

            PdfContentByte content;

            for (int pageNumber = 1; pageNumber <= totalPages; pageNumber++) {
                pageSize = reader.getPageSize(pageNumber);
                x = (pageSize.getLeft() + pageSize.getRight()) / 2;
                y = (pageSize.getTop() + pageSize.getBottom()) / 2;

                content = stamper.getUnderContent(pageNumber);
                content.addImage(watermarkImage, 200, 0, 0, 200, x, y, false);
            }

            stamper.close();
            reader.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
