/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class InterventoPlForLayerDto extends InterventoForLayerDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -989828460054927321L;
	
	private String superficieTagliataHa;

	/**
	 * @return the superficieTagliataHa
	 */
	public String getSuperficieTagliataHa() {
		return superficieTagliataHa;
	}

	/**
	 * @param superficieTagliataHa the superficieTagliataHa to set
	 */
	public void setSuperficieTagliataHa(String superficieTagliataHa) {
		this.superficieTagliataHa = superficieTagliataHa;
	}
	
	

}
