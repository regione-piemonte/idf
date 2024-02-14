/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoParametroTrasfEnum {
	GOVERNO(1),
	CATEGORIA_FORESTALE(2),
	UBICAZIONE(3),
	DESTINAZIONI(4),
	TRASFORMAZIONE(5);
	
	private int value;
	
	private TipoParametroTrasfEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
