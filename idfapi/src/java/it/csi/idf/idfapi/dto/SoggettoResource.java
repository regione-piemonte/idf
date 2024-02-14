/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class SoggettoResource {
	private Integer idSoggetto;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String partitaIva;
	private String denominazione;
	private Integer flgEntePubblico;
	
	public Integer getIdSoggetto() {
		return idSoggetto;
	}
	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Integer getFlgEntePubblico() {
		return flgEntePubblico;
	}

	public void setFlgEntePubblico(Integer flgEntePubblico) {
		this.flgEntePubblico = flgEntePubblico;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SoggettoResource [idSoggetto=");
		builder.append(idSoggetto);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", cognome=");
		builder.append(cognome);
		builder.append(", codiceFiscale=");
		builder.append(codiceFiscale);
		builder.append(", partitaIva=");
		builder.append(partitaIva);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append("]");
		return builder.toString();
	}
}
