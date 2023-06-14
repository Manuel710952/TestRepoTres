package com.maesquiv.descargaarchivos;

public class PojoTablaTasas {
	public String mes;
	public String anio;
	public String mn;
	public String usd;
	public String udis;
	
	
	public PojoTablaTasas(String mes, String anio, String mn, String usd, String udis) {
		super();
		this.mes = mes;
		this.anio = anio;
		this.mn = mn;
		this.usd = usd;
		this.udis = udis;
	}

	
	public PojoTablaTasas() {
	}

	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public String getAnio() {
		return anio;
	}


	public void setAnio(String anio) {
		this.anio = anio;
	}


	public String getMn() {
		return mn;
	}


	public void setMn(String mn) {
		this.mn = mn;
	}


	public String getUsd() {
		return usd;
	}


	public void setUsd(String usd) {
		this.usd = usd;
	}


	public String getUdis() {
		return udis;
	}


	public void setUdis(String udis) {
		this.udis = udis;
	}


	@Override
	public String toString() {
		return "PojoTablaTasas [mes=" + mes + ", anio=" + anio + ", mn=" + mn + ", usd=" + usd + ", udis=" + udis + "]";
	}
	
	
}
