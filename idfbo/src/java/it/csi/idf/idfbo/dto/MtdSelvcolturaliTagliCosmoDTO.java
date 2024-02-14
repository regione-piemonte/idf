/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.util.Date;

public class MtdSelvcolturaliTagliCosmoDTO {

	private long idPratica;
	private long numeroPratica;
	private Date dataInvioPratica;
	private Integer annoPratica;
	private String denominazioneRichiedente;
	private String cognomeRichiedente;
	private String nomeRichiedente;
	private String pgPartitaIvaRichiedente;
	private String pgCodiceFiscaleRichiedente;
	private String pgIndirizzoPecRichiedente;
	private String pfCodiceFiscaleRichiedente;
	private String pfIndirizzoMail;
	private String siglaProvincia;
	private String comune;

	public long getIdPratica() {
		return idPratica;
	}

	public void setIdPratica(long idPratica) {
		this.idPratica = idPratica;
	}

	public long getNumeroPratica() {
		return numeroPratica;
	}

	public void setNumeroPratica(long numeroPratica) {
		this.numeroPratica = numeroPratica;
	}

	public Date getDataInvioPratica() {
		return dataInvioPratica;
	}

	public void setDataInvioPratica(Date dataInvioPratica) {
		this.dataInvioPratica = dataInvioPratica;
	}

	public Integer getAnnoPratica() {
		return annoPratica;
	}

	public void setAnnoPratica(Integer annoPratica) {
		this.annoPratica = annoPratica;
	}

	public String getDenominazioneRichiedente() {
		return denominazioneRichiedente;
	}

	public void setDenominazioneRichiedente(String denominazioneRichiedente) {
		this.denominazioneRichiedente = denominazioneRichiedente;
	}

	public String getCognomeRichiedente() {
		return cognomeRichiedente;
	}

	public void setCognomeRichiedente(String cognomeRichiedente) {
		this.cognomeRichiedente = cognomeRichiedente;
	}

	public String getNomeRichiedente() {
		return nomeRichiedente;
	}

	public void setNomeRichiedente(String nomeRichiedente) {
		this.nomeRichiedente = nomeRichiedente;
	}

	public String getPgPartitaIvaRichiedente() {
		return pgPartitaIvaRichiedente;
	}

	public void setPgPartitaIvaRichiedente(String pgPartitaIvaRichiedente) {
		this.pgPartitaIvaRichiedente = pgPartitaIvaRichiedente;
	}

	public String getPgCodiceFiscaleRichiedente() {
		return pgCodiceFiscaleRichiedente;
	}

	public void setPgCodiceFiscaleRichiedente(String pgCodiceFiscaleRichiedente) {
		this.pgCodiceFiscaleRichiedente = pgCodiceFiscaleRichiedente;
	}

	public String getPgIndirizzoPecRichiedente() {
		return pgIndirizzoPecRichiedente;
	}

	public void setPgIndirizzoPecRichiedente(String pgIndirizzoPecRichiedente) {
		this.pgIndirizzoPecRichiedente = pgIndirizzoPecRichiedente;
	}

	public String getPfCodiceFiscaleRichiedente() {
		return pfCodiceFiscaleRichiedente;
	}

	public void setPfCodiceFiscaleRichiedente(String pfCodiceFiscaleRichiedente) {
		this.pfCodiceFiscaleRichiedente = pfCodiceFiscaleRichiedente;
	}

	public String getPfIndirizzoMail() {
		return pfIndirizzoMail;
	}

	public void setPfIndirizzoMail(String pfIndirizzoMail) {
		this.pfIndirizzoMail = pfIndirizzoMail;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

}
