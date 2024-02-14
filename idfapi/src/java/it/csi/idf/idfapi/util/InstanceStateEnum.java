/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum InstanceStateEnum {
	BOZZA(1),
	INVIATA(2),
	VERIFICATA(3),
	RIFIUTATA(4),
	ARCHIVIATA(5),
	SCADUTA(6),
	ANNULLATA(7);
	
	private int value;
	
	private InstanceStateEnum(int value) {
		this.value = value;
	}
	
	public int getStateValue() {
		return value;
	}
}
