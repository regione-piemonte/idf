/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

public class VincoloStep4DTO {
	
	public Integer flagCauzione;
	
	public Integer getFlagCauzione() {
		return flagCauzione;
	}

	public void setFlagCauzione(Integer flagCauzione) {
		this.flagCauzione = flagCauzione;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nVincoloStep4DTO:");
		builder.append("\n\tflagCauzione = " + flagCauzione + "\n");
		return builder.toString();
	}
}
