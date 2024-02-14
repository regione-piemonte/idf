/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoUtilizzatoreTagliEnum {
	NO_DATA("No data", 0),
	IN_PROPRIO("In proprio", 1),
	DA_INDIVIDUARE("Da individuare ", 2),
	PERSONA_FISICA("Persona fisica", 3),
	PERSONA_GIURIDICA("Persona giuridica", 4);

	private String text;
	private int value;

	private TipoUtilizzatoreTagliEnum(String text, int value) {
		this.text = text;
		this.value = value;
	}
	
	public String getText() {
		return text;
	}
	
	public int getValue() {
		return value;
	}
}
