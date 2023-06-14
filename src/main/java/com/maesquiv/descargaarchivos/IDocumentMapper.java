package com.maesquiv.descargaarchivos;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

public interface IDocumentMapper<T> {
	List<T> getTasas(String urlDownloadFile);
	String covertCell(Cell cell);
}
