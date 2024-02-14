/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoRichiedenteEnum {
	NO_DATA(0),
	PROPRIETARIO(1),
	ACQUIRENTE(2),
	POSSESSORE(3),
	UTILIZZATORE(4),
	GESTORE(5);

	private int value;

	private TipoRichiedenteEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
