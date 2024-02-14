/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum RelascopicaCompletaEnum {
	
	SPECIE("Specie"),
	TIPO("Tipo"),
	DIAMETRO("Diametro"),
	ALTEZZA("Altezza"),
	INCREMENTO("Incremento");
	
	private String value;
	private RelascopicaCompletaEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
