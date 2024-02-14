/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum AreaDiSaggioEnum {
	
	CODICE_ADS("Codice ADS"), 
	COMUNE("Comune"),
	CATEGORIA_FORESTALE("Categoria forestale"),
	AMBITO_DI_RILEVAMENTO("Ambito di rilevamento"),
	DETTAGLIO_AMBITO("Dettaglio ambito"),
	TIPOLOGIA("Tipologia"),
	DATA_RILEVAMENTO("Data rilevamento"),
	STATO("Stato");
	
	private String value;
	private AreaDiSaggioEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
