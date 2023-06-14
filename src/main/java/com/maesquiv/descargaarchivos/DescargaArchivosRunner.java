package com.maesquiv.descargaarchivos;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DescargaArchivosRunner {
	
	public static void main(String[] args) {
		final String URL_DESCARGA = "https://www.cotizadorvida.mx/assets/pdfs/tasasAcreditadas2019.xlsx";
        String filePath = "C:/reportes/tasasAcreditadas2019.xlsx";

        try {
            TasasDeInteresService servicioGetConversionTasas = new TasasDeInteresService(new DocumentMapperImpl());
            List<PojoTablaTasas> listaTasas = servicioGetConversionTasas.getTasasDeInteres(URL_DESCARGA);
            Collections.sort(listaTasas, Comparator.comparing(PojoTablaTasas::getAnio).reversed());
            for(PojoTablaTasas tasa: listaTasas) {
            	System.out.println("la tasa es::" + tasa.toString());
            }
        } catch (Exception e) {
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
	    List<PojoTablaTasas> tasas = new ArrayList<>();
	    try (InputStream in = new BufferedInputStream(fileUrl.openStream())) {
	        Workbook workbook = new XSSFWorkbook(in);
	        Sheet sheet = workbook.getSheetAt(0);
	        
	        for (Row row : sheet) {
	        	PojoTablaTasas tasa = new PojoTablaTasas();
	            for (Cell cell : row) {
	            	if(cell.getRowIndex() > 4 &&  cell.getColumnIndex() <= 5) {
	            		//System.out.println("valor celda:: " + cell.getRowIndex());
		                String cellValue;
		                String valorTmp = "";
		                if (cell.getCellType() == CellType.NUMERIC) {
		                    cellValue = String.valueOf(cell.getNumericCellValue());
		                    DecimalFormat df = new DecimalFormat("#.########");
		                    String porcentajeRedondeado = df.format(cell.getNumericCellValue() * 100) + "%";
		                    //System.out.println(porcentajeRedondeado);
		                    valorTmp = porcentajeRedondeado;
		                } else {
		                    cellValue = cell.getStringCellValue();
		                    valorTmp = cellValue;
		                    //System.out.println(cellValue);
		                }
		                
		                /*switch(cell.getColumnIndex()) {
	            			case 1:
	            				tasa.setMes(valorTmp);
	            			break;
	            			case 2:
	            				tasa.setAnio(valorTmp);
		            			break;
	            			case 3:
	            				tasa.setMn(valorTmp);
		            			break;
	            			case 4:
	            				tasa.setUsd(valorTmp);
		            			break;
	            			case 5:
	            				tasa.setUdis(valorTmp);
		            			break;
	            			default:
		            			break;
	            		}*/
	            	}
	            }
	            //System.out.println("############################################    TERMINO LINREA    ###########################################");
	            if(tasa.getMes() != null && tasa.getMes() != "") {
	            	tasas.add(tasa);
	            }
	        }
	        
	        System.out.println("las tasas son::" + tasas.toString());
	    }
	}
}
