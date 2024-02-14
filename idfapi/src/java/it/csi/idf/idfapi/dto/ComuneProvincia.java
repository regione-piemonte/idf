/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class ComuneProvincia {
	private int idComune;
	private String istatComune;
	private String denomComune;
	private String istatProvincia;
	private String denomProvincia;
	public int getIdComune() {
		return idComune;
	}
	public void setIdComune(int idComune) {
		this.idComune = idComune;
	}
	public String getIstatComune() {
		return istatComune;
	}
	public void setIstatComune(String istatComune) {
		this.istatComune = istatComune;
	}
	public String getDenomComune() {
		return denomComune;
	}
	public void setDenomComune(String denomComune) {
		this.denomComune = denomComune;
	}
	public String getIstatProvincia() {
		return istatProvincia;
	}
	public void setIstatProvincia(String istatProvincia) {
		this.istatProvincia = istatProvincia;
	}
	public String getDenomProvincia() {
		return denomProvincia;
	}
	public void setDenomProvincia(String denomProvincia) {
		this.denomProvincia = denomProvincia;
	}
	
}
