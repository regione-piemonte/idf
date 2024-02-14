/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum AmbitoIstanzaEnum {
	
	TRASFORMAZIONE_DEL_BOSCO(1),
	TAGLIO_IN_BOSCO(2),		
	VINCOLO_IDROGEOLOGICO(3);
	
	private int value;
	private AmbitoIstanzaEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

}
