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

public class IstanzaDetails {

	private String tipoDiComunicazione;
	private String numeroIstanza;
	private String descrizione;
	private String comune;
	private LocalDate dataDiCompilazione;
	private LocalDate dataPrevistaPerInizio;
	private String descProprietaPrimpa;

	public String getTipoDiComunicazione() {
		return tipoDiComunicazione;
	}

	public void setTipoDiComunicazione(String tipoDiComunicazione) {
		this.tipoDiComunicazione = tipoDiComunicazione;
	}

	public String getNumeroIstanza() {
		return numeroIstanza;
	}

	public void setNumeroIstanza(String numeroIstanza) {
		this.numeroIstanza = numeroIstanza;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataDiCompilazione() {
		return dataDiCompilazione;
	}

	public void setDataDiCompilazione(LocalDate dataDiCompilazione) {
		this.dataDiCompilazione = dataDiCompilazione;
	}

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataPrevistaPerInizio() {
		return dataPrevistaPerInizio;
	}

	public void setDataPrevistaPerInizio(LocalDate dataPrevistaPerInizio) {
		this.dataPrevistaPerInizio = dataPrevistaPerInizio;
	}

	public String getDescProprietaPrimpa() {
		return descProprietaPrimpa;
	}

	public void setDescProprietaPrimpa(String descProprietaPrimpa) {
		this.descProprietaPrimpa = descProprietaPrimpa;
	}
	
	

}
