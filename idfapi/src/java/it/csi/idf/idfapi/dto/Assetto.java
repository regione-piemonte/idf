/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Assetto {
	
	private Integer idAssetto;
	private String codAssetto;
	private String descAssetto;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	public Integer getIdAssetto() {
		return idAssetto;
	}
	public void setIdAssetto(Integer idAssetto) {
		this.idAssetto = idAssetto;
	}
	public String getCodAssetto() {
		return codAssetto;
	}
	public void setCodAssetto(String codAssetto) {
		this.codAssetto = codAssetto;
	}
	public String getDescAssetto() {
		return descAssetto;
	}
	public void setDescAssetto(String descAssetto) {
		this.descAssetto = descAssetto;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	
	
}
