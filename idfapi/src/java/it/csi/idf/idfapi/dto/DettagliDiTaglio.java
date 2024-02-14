/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class DettagliDiTaglio {

	private Integer volumeRamaglia;
	private Integer stimaMassaRetraibileM3;
	private String usoViabilita;
	private String esbosco;
	private String noteEsbosco;
	private String tipoIstanzaTrasmessa;
	private LocalDate dataTrasmissione;
	private Integer numeroIstanza;
	private Integer numeroPiante;
	private String flgConformeDeroga;
	
	public Integer getVolumeRamaglia() {
		return volumeRamaglia;
	}
	public void setVolumeRamaglia(Integer volumeRamaglia) {
		this.volumeRamaglia = volumeRamaglia;
	}
	public Integer getStimaMassaRetraibileM3() {
		return stimaMassaRetraibileM3;
	}
	public void setStimaMassaRetraibileM3(Integer stimaMassaRetraibileM3) {
		this.stimaMassaRetraibileM3 = stimaMassaRetraibileM3;
	}
	public String getUsoViabilita() {
		return usoViabilita;
	}
	public void setUsoViabilita(String usoViabilita) {
		this.usoViabilita = usoViabilita;
	}
	public String getEsbosco() {
		return esbosco;
	}
	public void setEsbosco(String esbosco) {
		this.esbosco = esbosco;
	}
	public String getNoteEsbosco() {
		return noteEsbosco;
	}
	public void setNoteEsbosco(String noteEsbosco) {
		this.noteEsbosco = noteEsbosco;
	}
	public String getTipoIstanzaTrasmessa() {
		return tipoIstanzaTrasmessa;
	}
	public void setTipoIstanzaTrasmessa(String tipoIstanzaTrasmessa) {
		this.tipoIstanzaTrasmessa = tipoIstanzaTrasmessa;
	}
	public LocalDate getDataTrasmissione() {
		return dataTrasmissione;
	}
	public void setDataTrasmissione(LocalDate dataTrasmissione) {
		this.dataTrasmissione = dataTrasmissione;
	}
	public Integer getNumeroIstanza() {
		return numeroIstanza;
	}
	public void setNumeroIstanza(Integer numeroIstanza) {
		this.numeroIstanza = numeroIstanza;
	}
	public Integer getNumeroPiante() {
		return numeroPiante;
	}
	public void setNumeroPiante(Integer numeroPiante) {
		this.numeroPiante = numeroPiante;
	}
	public String getFlgConformeDeroga() {
		return flgConformeDeroga;
	}
	public void setFlgConformeDeroga(String flgConformeDeroga) {
		this.flgConformeDeroga = flgConformeDeroga;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DettagliDiTaglio [volumeRamaglia=");
		builder.append(volumeRamaglia);
		builder.append(", stimaMassaRetraibileM3=");
		builder.append(stimaMassaRetraibileM3);
		builder.append(", usoViabilita=");
		builder.append(usoViabilita);
		builder.append(", esbosco=");
		builder.append(esbosco);
		builder.append(", noteEsbosco=");
		builder.append(noteEsbosco);
		builder.append(", tipoIstanzaTrasmessa=");
		builder.append(tipoIstanzaTrasmessa);
		builder.append(", dataTrasmissione=");
		builder.append(dataTrasmissione);
		builder.append(", numeroIstanza=");
		builder.append(numeroIstanza);
		builder.append(", numeroPiante=");
		builder.append(numeroPiante);
		builder.append(", flgConformeDeroga=");
		builder.append(flgConformeDeroga);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
}
