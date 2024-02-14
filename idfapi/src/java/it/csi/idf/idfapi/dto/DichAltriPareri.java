/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class DichAltriPareri {
	private String etichetta;
	private boolean checked;
	private String testo;
	
	public String getEtichetta() {
		return etichetta;
	}
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DichAltriPareri [etichetta=");
		builder.append(etichetta);
		builder.append(", checked=");
		builder.append(checked);
		builder.append(", testo=");
		builder.append(testo);
		builder.append("]");
		return builder.toString();
	}
}
