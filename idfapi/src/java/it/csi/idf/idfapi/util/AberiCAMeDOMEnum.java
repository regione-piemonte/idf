/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum AberiCAMeDOMEnum {
	
	CODICE_ADS("Codice Ads"),
	ALBERI_DOMINANTI_E_CAMPIONE("Alberi dominanti e campione"), 
	SPECIE("Specie"),
	QUALITA("Qualita"),
	DIAMETRO("Diametro"),
	ALTEZZA("Altezza"),
	INCREMENTO("Incremento"),
	ETA("Eta"),
	NOTE("Note");
	
	private String value;
	private AberiCAMeDOMEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
