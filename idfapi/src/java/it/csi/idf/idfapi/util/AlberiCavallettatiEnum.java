/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum AlberiCavallettatiEnum {
	
	//CODICE("Codice"),
	SPECIE("Specie"),
	//GRUPPO("Gruppo"),
	DIAMETRO("Diametro (cm a 1,30m)"),
	SEME_POLLONE("Seme/pollone");
	
	private String value;
	private AlberiCavallettatiEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
