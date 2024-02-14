/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipologiaEnum {
//	A_SUPERFICIE_NOTA("A SUPERFICIE NOTA"), 
//	RELASCOPICA_SEMPLICE("RELASCOPICA SEMPLICE"),
//	RELASCOPICA_COMPLETA("RELASCOPICA COMPLETA");
	
	A_SUPERFICIE_NOTA("A SUPERFICIE NOTA"),
	RELASCOPICA_SEMPLICE("RELASCOPICA SEMPLICE"),
	RELASCOPICA_COMPLETA("RELASCOPICA COMPLETA");
	
	private String value;
	private TipologiaEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
