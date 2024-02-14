/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TrasformazCatastoXls {

	private Integer idIstanza;
	private String anno;
	private String comune;
	private String sezione;
	private Integer foglio;
	private String particella;
	
	public Integer getIdIstanza() {
		return idIstanza;
	}
	public String getAnno() {
		return anno;
	}
	public String getComune() {
		return comune;
	}
	public String getSezione() {
		return sezione;
	}
	public Integer getFoglio() {
		return foglio;
	}
	public String getParticella() {
		return particella;
	}
	public void setIdIstanza(Integer idIstanza) {
		this.idIstanza = idIstanza;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public void setFoglio(Integer foglio) {
		this.foglio = foglio;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	
	
}
