package com.maesquiv.descargaarchivos;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DescargaArchivosRunner {
	public static void main(String[] args) {
		final String URL_DESCARGA = "https://www.cotizadorvida.mx/assets/pdfs/tasasAcreditadas2019.xlsx";
        String filePath = "C:/reportes/tasasAcreditadas2019.xlsx";

        try {
            // Descargar el archivo de la URL
            downloadFile(URL_DESCARGA, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/* private static void downloadFile(String url, String filePath) throws IOException {
	        URL fileUrl = new URL(url);
	        try (InputStream in = new BufferedInputStream(fileUrl.openStream())) {
	            Path targetPath = Path.of(filePath);
	            Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
	        }
	    }*/
	 
	private static void downloadFile(String url, String filePath) throws IOException {
	    URL fileUrl = new URL(url);
	    try (InputStream in = new BufferedInputStream(fileUrl.openStream())) {
	        Workbook workbook = new XSSFWorkbook(in);
	        Sheet sheet = workbook.getSheetAt(0);

	        for (Row row : sheet) {
	            for (Cell cell : row) {
	            	if(cell.getRowIndex() > 3 &&  cell.getColumnIndex() <= 5) {
	            		System.out.println("valor celda:: " + cell.getColumnIndex());
		                String cellValue;
		                if (cell.getCellType() == CellType.NUMERIC) {
		                    cellValue = String.valueOf(cell.getNumericCellValue());
		                    DecimalFormat df = new DecimalFormat("#.########");
		                    String porcentajeRedondeado = df.format(cell.getNumericCellValue() * 100) + "%";
		                    System.out.println(porcentajeRedondeado);
		                } else {
		                    cellValue = cell.getStringCellValue();
		                    System.out.println(cellValue);
		                }
		                
		           
	            	}
	            }
	            System.out.println("############################################    TERMINO LINREA    ###########################################");
	        }
	    }
	}
}
