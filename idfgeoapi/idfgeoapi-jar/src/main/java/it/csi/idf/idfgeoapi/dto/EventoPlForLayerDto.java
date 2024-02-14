/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class EventoPlForLayerDto extends EventoForLayerDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6345793545417826187L;
	
	private Integer superficieHa;
	
	/**
	 * @return the superficieHa
	 */
	public Integer getSuperficieHa() {
		return superficieHa;
	}
	/**
	 * @param superficieHa the superficieHa to set
	 */
	public void setSuperficieHa(Integer superficieHa) {
		this.superficieHa = superficieHa;
	}
	
	
}
