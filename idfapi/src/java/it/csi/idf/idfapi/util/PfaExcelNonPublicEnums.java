/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum PfaExcelNonPublicEnums {

	DENOMINAZIONE("Denominazione"),
	PROVINCE("Provincia"),
	COMUNI("Comuni interessati"),
	DATA_INIZIO("Data inizio"),
	DATA_FINE("Data fine"),
	N_E_DATA_DGR_APPROVAZIONE("Approvazione DGR"),
	GESTORE("Gestore"),
	FONTE_FINANZIAMENTO("Fonte di finanziamento"),
	PROPRIETA("Proprieta'"),
	REVISIONE("Revisione"),	
	PROPRIETA_SILVOPAST("Proprieta Silvo pastorale (ha)"),
	PROPRIETA_FORESTALE("Proprieta Forestale (ha)"),	
	PROPRIETA_NON_FORESTALE("Proprieta non Forestale (ha)"),
	SUPERFICIA_PIANIFICATA_NONFORESTALE("Superficie pianificata non forestale (ha)"),
	SUPERFICIA_PIANIFICATA_FORESTALE("Superficie pianificata forestale (ha)"),
	SUPERFICIA_GEST_NONATTIVA_TOT("Superficie totale a gestione non attiva (ha)"),
	SUPERFICIA_BOCS_GESTATTIVA("Superficie boscata a gesione attiva (ha)"),	
	SUPERFICIA_GEST_NONATTIVA_MON("Superficie in monitoraggio (ha)"),
	SUPERFICIA_GEST_NONATTIVAEVL("Superficie in evoluzione libera (ha)");

	private String value;

	private PfaExcelNonPublicEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
