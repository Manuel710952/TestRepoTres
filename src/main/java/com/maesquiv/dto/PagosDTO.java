package com.maesquiv.dto;

import java.io.Serializable;

public class PagosDTO implements Serializable {
	
	private static final long serialVersionUID = 6480573155901780777L;
	
	private int folio;
	private String descripcion;
	private double importe;
	
	public PagosDTO() {}
	
	public PagosDTO(int folio, String descripcion, double importe) {
		super();
		this.folio = folio;
		this.descripcion = descripcion;
		this.importe = importe;
	}
	
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	@Override
	public String toString() {
		return "PagosDTO [folio=" + folio + ", descripcion=" + descripcion + ", importe=" + importe + "]";
	}
	
	
}
