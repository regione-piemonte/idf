/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class DichIstanzaTaglio {
	
	private String etichetta;
	private boolean visible;
	private boolean checked;
	private boolean required;
	private List<IstanzaTaglio> istanziList;
	
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
	public List<IstanzaTaglio> getIstanziList() {
		return istanziList;
	}
	public void setIstanziList(List<IstanzaTaglio> istanziTaglio) {
		this.istanziList = istanziTaglio;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DichIstanzaTaglio [etichetta=");
		builder.append(etichetta);
		builder.append(", visible=");
		builder.append(visible);
		builder.append(", checked=");
		builder.append(checked);
		builder.append(", required=");
		builder.append(required);
		builder.append(", istanziList=");
		builder.append(istanziList);
		builder.append("]");
		return builder.toString();
	}
}
