/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class LabelValore {
	private String label;
	private String valore;
	
	public String getLabel() {
		return label;
	}
	public String getValore() {
		return valore;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
}
