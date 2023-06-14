package com.maesquiv.descargaarchivos;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DocumentMapperImpl implements IDocumentMapper<PojoTablaTasas>{

	@Override
	public List<PojoTablaTasas> getTasas(String urlDownloadFile) {
	    List<PojoTablaTasas> tasas = null;
	    try {
	    	// descarga de archivo
	    	URL fileUrl = new URL(urlDownloadFile);
	    	InputStream in = new BufferedInputStream(fileUrl.openStream());
	        Workbook workbook = new XSSFWorkbook(in);
	        Sheet sheet = workbook.getSheetAt(0);
	        
	        tasas = new ArrayList<>();
	        for (Row row : sheet) {
	        	Map<String, Object> mapTasas = new HashMap<>();
	        	Iterator<Cell> iteradorCeldas = row.cellIterator();
	        	
	        	// se filtran las columnas de tasas
	        	Stream<Cell> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iteradorCeldas, 0), false);
	        	List<Cell> celdasTasas = stream
	        			.filter(c -> c.getRowIndex() > ContantesMapper.ROW_INDEX_INICIO &&  c.getColumnIndex() <= ContantesMapper.COLUMN_INDEX_FINAL)
	        			.toList();
	            for (Cell cell : celdasTasas) {
	            	String valorCelda = this.covertCell(cell);
            		mapTasas.put(ContantesMapper.RELACION_CAMPO_CELDA.get(cell.getColumnIndex()), valorCelda);
	            }
	            
	            if(mapTasas.size() > 0) {
	            	PojoTablaTasas tasa = MapperRowSheetUtil.convertMapToObject(mapTasas, PojoTablaTasas.class);
	            	tasas.add(tasa);
	            }
	        }
	    }
	    catch (Exception e) {
	    	System.out.println("Ocurrio un error al obtener listado de tasas de inetres");
		}
	    //System.out.println("las tasas de inetres son::" + tasas.toString());
	    return tasas;
	}

	@Override
	public String covertCell(Cell cell) {
		String valorFormateado = null;
		try {
			if(cell.getCellType() == CellType.NUMERIC) {
				DecimalFormat df = new DecimalFormat(ContantesMapper.FORMAT_VALOR_PORCENTAJE);
				valorFormateado = (cell.getColumnIndex() > 2) 
						? df.format(cell.getNumericCellValue() * 100)
						: String.valueOf((int) cell.getNumericCellValue());
			}
			else {
				valorFormateado = cell.getStringCellValue();
			}
		}
		catch (Exception e) {
			System.out.println("Ocurrio un error al obtener valor de celda");
		}
		return valorFormateado;
	}

}
