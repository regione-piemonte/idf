/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum InterventoExcelEnum {
	
	PFA_DENOMINAZIONE("Denominazione PGF"),
	N_INTERVENTO("N. Intervento"),
	ANNATA_SILVANA("Annata silvana"),
	PARTICELA_FORESTALE("N. Particella Forestale"),
	DATA_INIZIO("Data inizio"),
	DATA_FINE("Data fine"),
	DESCRIZIONE("Descrizione"),
	LOCALITA("Localita"),
	SUPERCFICIE_INTERESSATA("Superficie interessata (ha)"),
	M3_PRELEVATI("M\u00B3 prelevati"),
	STATO_INTERVENTO("Stato Intervento"),
	NR_ISTANZA_FORESTALE("N. Istanza");
	
	private String value;

	private InterventoExcelEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
