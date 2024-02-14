/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class InterventoLnForLayerDto extends InterventoForLayerDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7495094730112289428L;
	
	private String lunghezzaMetri;

	/**
	 * @return the lunghezzaMetri
	 */
	public String getLunghezzaMetri() {
		return lunghezzaMetri;
	}

	/**
	 * @param lunghezzaMetri the lunghezzaMetri to set
	 */
	public void setLunghezzaMetri(String lunghezzaMetri) {
		this.lunghezzaMetri = lunghezzaMetri;
	}
	
	

}
