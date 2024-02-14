/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import it.csi.idf.idfapi.util.TipoTitolaritaEnum;

public class PlainPrimaSezione {
	private Integer tipoIstanzaId;
	private String tipoIstanzaDescr;
	private TipoTitolaritaEnum personaDatiOption;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private ComuneResource comune;
	private String indirizzo;
	private String civico;
	private String cap;
	private String nrTelefonico;
	private String eMail;
	private String denominazione;
	private String partitaIva;
	private String pec;
	private String ownerCodiceFiscale;
	
	public Integer getTipoIstanzaId() {
		return tipoIstanzaId;
	}
	public void setTipoIstanzaId(Integer tipoIstanzaId) {
		this.tipoIstanzaId = tipoIstanzaId;
	}
	public String getTipoIstanzaDescr() {
		return tipoIstanzaDescr;
	}
	public void setTipoIstanzaDescr(String tipoIstanzaDescr) {
		this.tipoIstanzaDescr = tipoIstanzaDescr;
	}

	public TipoTitolaritaEnum getPersonaDatiOption() {
		return personaDatiOption;
	}
	public void setPersonaDatiOption(TipoTitolaritaEnum personaDatiOption) {
		this.personaDatiOption = personaDatiOption;
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
	public ComuneResource getComune() {
		return comune;
	}
	public void setComune(ComuneResource comune) {
		this.comune = comune;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getNrTelefonico() {
		return nrTelefonico;
	}
	public void setNrTelefonico(String nrTelefonico) {
		this.nrTelefonico = nrTelefonico;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getPec() {
		return pec;
	}
	public void setPec(String pec) {
		this.pec = pec;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainPrimaSezione [tipoIstanzaId=");
		builder.append(tipoIstanzaId);
		builder.append(", tipoIstanzaDescr=");
		builder.append(tipoIstanzaDescr);
		builder.append(", personaDatiOption=");
		builder.append(personaDatiOption);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", cognome=");
		builder.append(cognome);
		builder.append(", codiceFiscale=");
		builder.append(codiceFiscale);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", indirizzo=");
		builder.append(indirizzo);
		builder.append(", civico=");
		builder.append(civico);
		builder.append(", cap=");
		builder.append(cap);
		builder.append(", nrTelefonico=");
		builder.append(nrTelefonico);
		builder.append(", eMail=");
		builder.append(eMail);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append(", partitaIva=");
		builder.append(partitaIva);
		builder.append(", pec=");
		builder.append(pec);
		builder.append("]");
		return builder.toString();
	}
	public String getOwnerCodiceFiscale() {
		return ownerCodiceFiscale;
	}
	public void setOwnerCodiceFiscale(String ownerCodiceFiscale) {
		this.ownerCodiceFiscale = ownerCodiceFiscale;
	}
}
