/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class PfaForLayerDto extends FeatureForLayerDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6425144227552966442L;
	
	public String denominazionePfa;
	public String descrizione;

	/**
	 * @return the denominazionePfa
	 */
	public String getDenominazionePfa() {
		return denominazionePfa;
	}

	/**
	 * @param denominazionePfa the denominazionePfa to set
	 */
	public void setDenominazionePfa(String denominazionePfa) {
		this.denominazionePfa = denominazionePfa;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	
	
}
