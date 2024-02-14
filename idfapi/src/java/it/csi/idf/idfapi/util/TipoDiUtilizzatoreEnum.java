/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoDiUtilizzatoreEnum {

	IN_PROPRIO(0),
	DA_INDIVIDUARE(1),
	RICERCA(2);
	
	private int value;

	private TipoDiUtilizzatoreEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
