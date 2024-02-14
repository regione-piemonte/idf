/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class GeoPLProvinciaSearch {
    private String istatProv;
	
	private String siglaProv;
	
	private String denominazioneProv;

	public String getIstatProv() {
		return istatProv;
	}

	public void setIstatProv(String istatProv) {
		this.istatProv = istatProv;
	}

	public String getSiglaProv() {
		return siglaProv;
	}

	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

	public String getDenominazioneProv() {
		return denominazioneProv;
	}

	public void setDenominazioneProv(String denominazioneProv) {
		this.denominazioneProv = denominazioneProv;
	}
	
	
}
