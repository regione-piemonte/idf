/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class DatiMailInvoIstanza {
	
	private Integer idIstanzaIntervento;
	private Integer nrIstanzaForestale;
	private String mailRichiedente;
	private Integer idSoggettoGestore;
	private String denominazioneGestore;
	private String telefonoGestore;
	private String mailGestore;
	private String pecGestore;
	
	public Integer getIdIstanzaIntervento() {
		return idIstanzaIntervento;
	}
	public Integer getNrIstanzaForestale() {
		return nrIstanzaForestale;
	}
	public String getMailRichiedente() {
		return mailRichiedente;
	}
	public Integer getIdSoggettoGestore() {
		return idSoggettoGestore;
	}
	public String getDenominazioneGestore() {
		return denominazioneGestore;
	}
	public String getTelefonoGestore() {
		return telefonoGestore;
	}
	public String getMailGestore() {
		return mailGestore;
	}
	public String getPecGestore() {
		return pecGestore;
	}
	public void setIdIstanzaIntervento(Integer idIstanzaIntervento) {
		this.idIstanzaIntervento = idIstanzaIntervento;
	}
	public void setNrIstanzaForestale(Integer nrIstanzaForestale) {
		this.nrIstanzaForestale = nrIstanzaForestale;
	}
	public void setMailRichiedente(String mailRichiedente) {
		this.mailRichiedente = mailRichiedente;
	}
	public void setIdSoggettoGestore(Integer idSoggettoGestore) {
		this.idSoggettoGestore = idSoggettoGestore;
	}
	public void setDenominazioneGestore(String denominazioneGestore) {
		this.denominazioneGestore = denominazioneGestore;
	}
	public void setTelefonoGestore(String telefonoGestore) {
		this.telefonoGestore = telefonoGestore;
	}
	public void setMailGestore(String mailGestore) {
		this.mailGestore = mailGestore;
	}
	public void setPecGestore(String pecGestore) {
		this.pecGestore = pecGestore;
	}
	
}
