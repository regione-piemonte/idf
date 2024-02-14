/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.json.JSONPropertyIgnore;

import java.util.Date;

public class TAIFAnagraficaAzienda {
	
	private String codiceFiscale;
	private String partitaIva;
	private String ragioneSociale;
	private String numeroAlbo;
	private Date dataIscrizioneAlbo;
	private Date dataAggiornamento;
	private int idStatoPratica;
	private String statoPratica ;
	private char flgSezione;
	private String istatComune;
	private String indirizzo;
	private String civico;
	private String cap;
	private String telefono;
	private String email;
	private String pec;
	private String codiceFiscaleTitolare;
	private String cognomeTitolare;
	private String nomeTitolare;

	@JsonIgnore
	private String label;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getNumeroAlbo() {
		return numeroAlbo;
	}
	public void setNumeroAlbo(String numeroAlbo) {
		this.numeroAlbo = numeroAlbo;
	}
	public Date getDataIscrizioneAlbo() {
		return dataIscrizioneAlbo;
	}
	public void setDataIscrizioneAlbo(Date dataIscrizioneAlbo) {
		this.dataIscrizioneAlbo = dataIscrizioneAlbo;
	}
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public int getIdStatoPratica() {
		return idStatoPratica;
	}
	public void setIdStatoPratica(int idStatoPratica) {
		this.idStatoPratica = idStatoPratica;
	}
	public String getStatoPratica() {
		return statoPratica;
	}
	public void setStatoPratica(String statoPratica) {
		this.statoPratica = statoPratica;
	}	
	public char getFlgSezione() {
		return flgSezione;
	}
	public void setFlgSezione(char flgSezione) {
		this.flgSezione = flgSezione;
	}
	public String getIstatComune() {
		return istatComune;
	}
	public void setIstatComune(String istatComune) {
		this.istatComune = istatComune;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodiceFiscaleTitolare() {
		return codiceFiscaleTitolare;
	}
	public void setCodiceFiscaleTitolare(String codiceFiscaleTitolare) {
		this.codiceFiscaleTitolare = codiceFiscaleTitolare;
	}
	public String getCognomeTitolare() {
		return cognomeTitolare;
	}
	public void setCognomeTitolare(String cognomeTitolare) {
		this.cognomeTitolare = cognomeTitolare;
	}
	public String getNomeTitolare() {
		return nomeTitolare;
	}
	public void setNomeTitolare(String nomeTitolare) {
		this.nomeTitolare = nomeTitolare;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
