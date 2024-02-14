/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class DichCompensazione {
	private String etichetta;
	private boolean visible;
	private boolean checked;
	private boolean required;
	
	public String getEtichetta() {
		return etichetta;
	}
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DichCompensazione [etichetta=");
		builder.append(etichetta);
		builder.append(", visible=");
		builder.append(visible);
		builder.append(", checked=");
		builder.append(checked);
		builder.append(", required=");
		builder.append(required);
		builder.append("]");
		return builder.toString();
	}
}
