/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoUtilizzatoreRicercaEnum {

	PERSONA_FISICA(0),
	PERSONA_GIURIDICA(1);
	
	private Integer value;

	private TipoUtilizzatoreRicercaEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	
}
