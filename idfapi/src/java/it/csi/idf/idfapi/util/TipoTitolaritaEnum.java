/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoTitolaritaEnum {
	//richiedente
	RF(1),
	RG(2),

	// professionista
	PF(3),
	PG(4),

	// sportello
	SF(5),
	SG(6);
	
	private Integer option;
	
	private TipoTitolaritaEnum(Integer option) {
		this.option = option;
	}
	
	public Integer getOption() {
		return option;
	}
	
	public static TipoTitolaritaEnum getTitolarita(int titolaritaIstanza) {
		for (TipoTitolaritaEnum titolo : TipoTitolaritaEnum.values()) { 
		    if(titolo.getOption() == titolaritaIstanza) {
		    	return titolo;
		    } 
		}
		throw new IllegalArgumentException("TitolaritaIstanzaEnum does not have provided parameter");
	}
	
	public static int getTitolaritaOption(TipoTitolaritaEnum titolarita) {
		for (TipoTitolaritaEnum titolo : TipoTitolaritaEnum.values()) { 
		    if(titolo == titolarita) {
		    	return titolo.getOption();
		    } 
		}
		throw new IllegalArgumentException("TitolaritaIstanzaEnum does not have provided enum");
	}
}
