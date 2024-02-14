/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TerzaSezioneSaveResult {
	
	private String warning;

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TerzaSezioneWarningHolder [warning=");
		builder.append(warning);
		builder.append("]");
		return builder.toString();
	}
}
