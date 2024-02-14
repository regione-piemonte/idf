/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import java.util.Arrays;
import java.util.Optional;

public enum TipoAccreditamentoEnum {
	NO_DATA("No data", 0),
	RICHIEDENTE("Richiedente", 1),
	PROFESSIONISTA("Professionista", 2),
	SPORTELLO("Sportello",3);

	private String text;
	private int value;
	
	private TipoAccreditamentoEnum(String text, int value) {
		this.text = text;
		this.value = value;
	}
	
	public String getText() {
		return text;
	}
	
	public int getValue() {
		return value;
	}

	public static TipoAccreditamentoEnum fromString(String value) {
		return Arrays.stream(TipoAccreditamentoEnum.values())
				.filter(accStatus -> accStatus.text.equals(value))
				.findFirst().orElse(TipoAccreditamentoEnum.NO_DATA);
	}
	public static TipoAccreditamentoEnum fromInteger(int value) {
		return Arrays.stream(TipoAccreditamentoEnum.values())
				.filter(accStatus -> accStatus.value == value)
				.findFirst().orElse(TipoAccreditamentoEnum.NO_DATA);
	}

}
