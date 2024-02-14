/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum PlPfaEnum {
	FONTE_FINAZ(1),
	FINANZA(2),
	FONTE_FIN(3);
	
	private int value;
	
	private PlPfaEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
