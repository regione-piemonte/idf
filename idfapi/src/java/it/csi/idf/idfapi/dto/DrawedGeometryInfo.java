/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class DrawedGeometryInfo {
	private String tipoGeometria;
	private String descrizione;
	private String particelleForet;
	private String puntoCoord;
	private String lunghezzaLinea;
	private String superficiePoligon;
	private String geometryInfo;
	
	
	public String getTipoGeometria() {
		return tipoGeometria;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public String getParticelleForet() {
		return particelleForet;
	}
	public String getPuntoCoord() {
		return puntoCoord;
	}
	public String getLunghezzaLinea() {
		return lunghezzaLinea;
	}
	public String getSuperficiePoligon() {
		return superficiePoligon;
	}
	public void setTipoGeometria(String tipoGeometria) {
		this.tipoGeometria = tipoGeometria;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setParticelleForet(String particelleForet) {
		this.particelleForet = particelleForet;
	}
	public void setPuntoCoord(String puntoCoord) {
		this.puntoCoord = puntoCoord;
	}
	public void setLunghezzaLinea(String lunghezzaLinea) {
		this.lunghezzaLinea = lunghezzaLinea;
	}
	public void setSuperficiePoligon(String superficiePoligon) {
		this.superficiePoligon = superficiePoligon;
	}
	public String getGeometryInfo() {
		return geometryInfo;
	}
	public void setGeometryInfo(String geometryInfo) {
		this.geometryInfo = geometryInfo;
	}
	
	
}
