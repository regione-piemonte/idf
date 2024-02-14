/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class ZonaAltimetrica {
	
	private Integer idgeoPlZonaAltimetrica;
	private Integer fkComune;
	private String sezione;
	private Integer foglio;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private String note;
	//private Object geometria;
	private Integer fkParamtrasfZonaAltimetrica;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	
	public Integer getIdgeoPlZonaAltimetrica() {
		return idgeoPlZonaAltimetrica;
	}
	public void setIdgeoPlZonaAltimetrica(Integer idgeoPlZonaAltimetrica) {
		this.idgeoPlZonaAltimetrica = idgeoPlZonaAltimetrica;
	}
	public Integer getFkComune() {
		return fkComune;
	}
	public void setFkComune(Integer fkComune) {
		this.fkComune = fkComune;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public Integer getFoglio() {
		return foglio;
	}
	public void setFoglio(Integer foglio) {
		this.foglio = foglio;
	}
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getFkParamtrasfZonaAltimetrica() {
		return fkParamtrasfZonaAltimetrica;
	}
	public void setFkParamtrasfZonaAltimetrica(Integer fkParamtrasfZonaAltimetrica) {
		this.fkParamtrasfZonaAltimetrica = fkParamtrasfZonaAltimetrica;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZonaAltimetrica [idgeoPlZonaAltimetrica=");
		builder.append(idgeoPlZonaAltimetrica);
		builder.append(", fkComune=");
		builder.append(fkComune);
		builder.append(", sezione=");
		builder.append(sezione);
		builder.append(", foglio=");
		builder.append(foglio);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", note=");
		builder.append(note);
		builder.append(", fkParamtrasfZonaAltimetrica=");
		builder.append(fkParamtrasfZonaAltimetrica);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append("]");
		return builder.toString();
	}
}
