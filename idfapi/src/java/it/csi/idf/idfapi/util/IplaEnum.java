/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum IplaEnum {

	PFA(3),
	INVENTARI(4),
	PRIMPA(8);

	private int value;
	
	private IplaEnum(int value) {
		this.value = value;
	}
	
	public int getTypeValue() {
		return value;
	}
}
