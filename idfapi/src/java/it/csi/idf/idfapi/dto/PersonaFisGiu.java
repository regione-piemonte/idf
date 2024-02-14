/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import it.csi.idf.idfapi.util.TipoTitolaritaEnum;

public class PersonaFisGiu {
	private Integer idSoggetto;
	private TipoTitolaritaEnum personaDatiOption;

	private Boolean flgEntePubblico;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String indirizzo;
	private String civico;
	private String cap;
	private String nrTelefonico;
	private String eMail;
	private ComuneResource comune;

	private String denominazione;
	private String partitaIva;
	private String pec;
	private String ownerCodiceFiscale;

	private String numAlboForestale;

	private Boolean isTAIF;
	private TAIFAnagraficaAzienda soggettoTaif;

	private String nrMartelloForestale;
	private String nrIscrizioneOrdine;

	private GeoPLProvinciaSearch provIscrizioneOrdine;



	public Integer getIdSoggetto() {
		return idSoggetto;
	}

	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
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

	public ComuneResource getComune() {
		return comune;
	}

	public void setComune(ComuneResource comune) {
		this.comune = comune;
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

	public String getOwnerCodiceFiscale() {
		return ownerCodiceFiscale;
	}

	public void setOwnerCodiceFiscale(String ownerCodiceFiscale) {
		this.ownerCodiceFiscale = ownerCodiceFiscale;
	}

	public Boolean getFlgEntePubblico() {
		return flgEntePubblico;
	}

	public void setFlgEntePubblico(Boolean flgEntePubblico) {
		this.flgEntePubblico = flgEntePubblico;
	}

	public String getNumAlboForestale() {
		return numAlboForestale;
	}

	public void setNumAlboForestale(String numAlboForestale) {
		this.numAlboForestale = numAlboForestale;
	}

	public Boolean getTAIF() {
		return isTAIF;
	}

	public void setTAIF(Boolean TAIF) {
		isTAIF = TAIF;
	}

	public String getNrMartelloForestale() {
		return nrMartelloForestale;
	}

	public void setNrMartelloForestale(String nrMartelloForestale) {
		this.nrMartelloForestale = nrMartelloForestale;
	}


	public String getNrIscrizioneOrdine() {
		return nrIscrizioneOrdine;
	}

	public void setNrIscrizioneOrdine(String nrIscrizioneOrdine) {
		this.nrIscrizioneOrdine = nrIscrizioneOrdine;
	}


	public GeoPLProvinciaSearch getProvIscrizioneOrdine() {
		return provIscrizioneOrdine;
	}

	public void setProvIscrizioneOrdine(GeoPLProvinciaSearch provIscrizioneOrdine) {
		this.provIscrizioneOrdine = provIscrizioneOrdine;
	}

	public TAIFAnagraficaAzienda getSoggettoTaif() {
		return soggettoTaif;
	}

	public void setSoggettoTaif(TAIFAnagraficaAzienda soggettoTaif) {
		this.soggettoTaif = soggettoTaif;
	}
}
