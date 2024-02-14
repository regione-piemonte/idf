/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class EventoLnForLayerDto extends EventoForLayerDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -216681318896313189L;
	
	private Integer lunghezzaMetri;
	/**
	 * @return the dataInserimento
	 */
	
	/**
	 * @return the lunghezzaMetri
	 */
	public Integer getLunghezzaMetri() {
		return lunghezzaMetri;
	}
	/**
	 * @param lunghezzaMetri the lunghezzaMetri to set
	 */
	public void setLunghezzaMetri(Integer lunghezzaMetri) {
		this.lunghezzaMetri = lunghezzaMetri;
	}
	
	
}

