/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum ProfiloUtenteEnum {
	CITTADINO(1),
	GESTORE(2),
	UFFICIO_TERRITORIALE(3),
	COMUNE(4),
	CONSULTAZIONE(5),
	NO_DATA(0),
	GESTORE_PFA(6),
	SPORTELLO(7),
	GESTORE_SPORTELLO(8);

	private int value;
	
	private ProfiloUtenteEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
