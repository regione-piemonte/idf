/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum ConformeDerogaEnum {
	
	C("Conforme"),
	D("Deroga");
	
	private String value;
	
	private ConformeDerogaEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
