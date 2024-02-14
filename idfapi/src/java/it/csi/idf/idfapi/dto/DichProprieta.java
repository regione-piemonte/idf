/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class DichProprieta {
	private String etichetta1;
	private String etichetta2;
	private boolean isOwner;
	
	public String getEtichetta1() {
		return etichetta1;
	}
	public void setEtichetta1(String etichetta1) {
		this.etichetta1 = etichetta1;
	}
	public String getEtichetta2() {
		return etichetta2;
	}
	public void setEtichetta2(String etichetta2) {
		this.etichetta2 = etichetta2;
	}
	@JsonProperty("isOwner")
	public boolean isOwner() {
		return isOwner;
	}
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DichProprieta [etichetta1=");
		builder.append(etichetta1);
		builder.append(", etichetta2=");
		builder.append(etichetta2);
		builder.append(", isOwner=");
		builder.append(isOwner);
		builder.append("]");
		return builder.toString();
	}
}
