/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class DichDissensi {
	private String etichetta;
	private boolean dissenso;
	
	@JsonProperty("etichetta")
	public String getEtichetta() {
		return etichetta;
	}
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}

	@JsonProperty("haDissenso")
	public boolean haDissenso() {
		return dissenso;
	}
	public void setHaDissenso(boolean haDissenso) {
		this.dissenso = haDissenso;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DichDissensi [etichetta=");
		builder.append(etichetta);
		builder.append(", haDissenso=");
		builder.append(dissenso);
		builder.append("]");
		return builder.toString();
	}
}
