/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum ProceduraEnum {
	PIANI_FORESTALI_AZIENDALI(1),
	INVENTARI_FORESTASLI(2),
	GESTIONE_ISTANZE_FORESTALI(3),
	GESTIONE_ISTANZE_FORESTALI_VINCOLO(33),
	IDF(0);
	
	private int value;
	
	private ProceduraEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}	
}
