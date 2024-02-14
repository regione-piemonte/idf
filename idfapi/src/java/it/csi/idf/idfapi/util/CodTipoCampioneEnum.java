/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum CodTipoCampioneEnum {
	DOM("Dominante"),
	CAM("Campione"),
	CS1("Campione S1"),
	CS2("Campione S2");
	
	private String value;

	private CodTipoCampioneEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
