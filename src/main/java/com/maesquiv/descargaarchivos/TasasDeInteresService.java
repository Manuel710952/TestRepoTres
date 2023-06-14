package com.maesquiv.descargaarchivos;

import java.util.List;

public class TasasDeInteresService {
	private IDocumentMapper<PojoTablaTasas> mapperDocument;

	public TasasDeInteresService(IDocumentMapper<PojoTablaTasas> mapperDocument) {
		super();
		this.mapperDocument = mapperDocument;
	}
	
	public List<PojoTablaTasas> getTasasDeInteres(String urlDownloadDocument) {
		return this.mapperDocument.getTasas(urlDownloadDocument);
	}
}
