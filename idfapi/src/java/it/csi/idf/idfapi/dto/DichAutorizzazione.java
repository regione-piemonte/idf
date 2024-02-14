/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class DichAutorizzazione {
	private String etichetta;
	private boolean checked;
	private boolean required;
	private boolean visible;
	private String numeroAutorizzazione;
	private LocalDate dataAutorizzazione;
	private String rilasciataAutorizzazione;
	
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
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getNumeroAutorizzazione() {
		return numeroAutorizzazione;
	}
	public void setNumeroAutorizzazione(String numeroAutorizzazione) {
		this.numeroAutorizzazione = numeroAutorizzazione;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataAutorizzazione() {
		return dataAutorizzazione;
	}
	public void setDataAutorizzazione(LocalDate dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}
	public String getRilasciataAutorizzazione() {
		return rilasciataAutorizzazione;
	}
	public void setRilasciataAutorizzazione(String rilasciataAutorizzazione) {
		this.rilasciataAutorizzazione = rilasciataAutorizzazione;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DichAutorizzazione [etichetta=");
		builder.append(etichetta);
		builder.append(", checked=");
		builder.append(checked);
		builder.append(", required=");
		builder.append(required);
		builder.append(", visible=");
		builder.append(visible);
		builder.append(", numeroAutorizzazione=");
		builder.append(numeroAutorizzazione);
		builder.append(", dataAutorizzazione=");
		builder.append(dataAutorizzazione);
		builder.append(", rilasciataAutorizzazione=");
		builder.append(rilasciataAutorizzazione);
		builder.append("]");
		return builder.toString();
	}
}
