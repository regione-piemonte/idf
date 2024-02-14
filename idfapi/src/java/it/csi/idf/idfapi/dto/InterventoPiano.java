/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InterventoPiano {

	private String pfaDenominazione;
	private Integer idIntervento;
	private Integer nrProgressivoInterv;
	private String annataSilvana;
	private Integer[] nParticelaForestale;
	private String[] codParticelaForestale;
	private String[] denominazioneParticella;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private String descrizione;
	private String localita;
	private BigDecimal superficieInteressata;
	private Integer m3Prelevati;
	private String descrStatoIntervento;
	private String comunicazioneDiTaglio;
	private String nrIstanzaForestale;
	
	
	
	public String[] getCodParticelaForestale() {
		return codParticelaForestale;
	}

	public void setCodParticelaForestale(String[] codParticelaForestale) {
		this.codParticelaForestale = codParticelaForestale;
	}

	public String getPfaDenominazione() {
		return pfaDenominazione;
	}
	
	public void setPfaDenominazione(String pfaDenominazione) {
		this.pfaDenominazione = pfaDenominazione;
	}
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	
	public Integer getNrProgressivoInterv() {
		return nrProgressivoInterv;
	}
	public void setNrProgressivoInterv(Integer nrProgressivoInterv) {
		this.nrProgressivoInterv = nrProgressivoInterv;
	}
	
	public String getAnnataSilvana() {
		return annataSilvana;
	}
	public void setAnnataSilvana(String annataSilvana) {
		this.annataSilvana = annataSilvana;
	}
	
	public Integer[] getnParticelaForestale() {
		return nParticelaForestale;
	}
	public void setnParticelaForestale(Integer[] nParticelaForestale) {
		this.nParticelaForestale = nParticelaForestale;
	}
	
	public String[] getDenominazioneParticella() {
		return denominazioneParticella;
	}
	public void setDenominazioneParticella(String[] denominazioneParticella) {
		this.denominazioneParticella = denominazioneParticella;
	}
	
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	
	public BigDecimal getSuperficieInteressata() {
		return superficieInteressata;
	}
	public void setSuperficieInteressata(BigDecimal superficieInteressata) {
		this.superficieInteressata = superficieInteressata;
	}
	
	public Integer getM3Prelevati() {
		return m3Prelevati;
	}
	public void setM3Prelevati(Integer m3Prelevati) {
		this.m3Prelevati = m3Prelevati;
	}
	
	public String getDescrStatoIntervento() {
		return descrStatoIntervento;
	}
	public void setDescrStatoIntervento(String descrStatoIntervento) {
		this.descrStatoIntervento = descrStatoIntervento;
	}
	
	public String getComunicazioneDiTaglio() {
		return comunicazioneDiTaglio;
	}
	public void setComunicazioneDiTaglio(String comunicazioneDiTaglio) {
		this.comunicazioneDiTaglio = comunicazioneDiTaglio;
	}
	public String getNrIstanzaForestale() {
		return nrIstanzaForestale;
	}
	public void setNrIstanzaForestale(String nrIstanzaForestale) {
		this.nrIstanzaForestale = nrIstanzaForestale;
	}
	
	
	
}
