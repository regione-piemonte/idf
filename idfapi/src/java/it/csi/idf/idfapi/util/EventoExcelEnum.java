/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum EventoExcelEnum {
		
		PFA_DENOMINAZIONE("Denominazione PGF"),
		N_EVENTO("N. evento"),
		NOME_BREVE("Nome breve"),
	    DATA_EVENTO("Data evento"),
	    PARTICELA_FORESTALE("N. particella forestale"),
	    TIPO_EVENTO("Tipo Evento"),
	    DESCRIZIONE("Descrizione"),
	    LOCALITA("Localita"),
	    SUPERFICIE_INTERESSATA("Superficie interessata (ha)"),
	    PERCENTUALE_DANNO("% danno");
	
	private String value;

	private EventoExcelEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
