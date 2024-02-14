/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Arrays;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class TipoInterventoDettaglio {

	private String conformeDeroga;
	private String annataSilvana;
	private Integer[] idParticelaForestale;
	private Integer progressivoIntervento;
	private LocalDate dataPresuntaIntervento;
	private LocalDate dataInizioIntervento;
	private LocalDate dataFineIntervento;
	private String eventoCorrelato;
	private String governo;
	private String tipoIntervento;
	private String richiedePiedilsta;
	private String descrizione;
	private String localita;
	private Integer superficieInteressata;
	private String statoIntervento;

	private Integer numeroPiante;
	private Integer m3Prelevati;

	private String fasciaAltimetrica;
	private String finalitaTaglio;
	private String destLegname;
	public String getConformeDeroga() {
		return conformeDeroga;
	}
	public void setConformeDeroga(String conformeDeroga) {
		this.conformeDeroga = conformeDeroga;
	}
	public String getAnnataSilvana() {
		return annataSilvana;
	}
	public void setAnnataSilvana(String annataSilvana) {
		this.annataSilvana = annataSilvana;
	}
	
	public Integer[] getIdParticelaForestale() {
		return idParticelaForestale;
	}
	public void setIdParticelaForestale(Integer[] idParticelaForestale) {
		this.idParticelaForestale = idParticelaForestale;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataPresuntaIntervento() {
		return dataPresuntaIntervento;
	}
	public void setDataPresuntaIntervento(LocalDate dataPresuntaIntervento) {
		this.dataPresuntaIntervento = dataPresuntaIntervento;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataInizioIntervento() {
		return dataInizioIntervento;
	}
	public void setDataInizioIntervento(LocalDate dataInizioIntervento) {
		this.dataInizioIntervento = dataInizioIntervento;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class) 
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataFineIntervento() {
		return dataFineIntervento;
	}
	public void setDataFineIntervento(LocalDate dataFineIntervento) {
		this.dataFineIntervento = dataFineIntervento;
	}
	public String getGoverno() {
		return governo;
	}
	public void setGoverno(String governo) {
		this.governo = governo;
	}
	public String getTipoIntervento() {
		return tipoIntervento;
	}
	public void setTipoIntervento(String tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}
	public String getRichiedePiedilsta() {
		return richiedePiedilsta;
	}
	public void setRichiedePiedilsta(String richiedePiedilsta) {
		this.richiedePiedilsta = richiedePiedilsta;
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
	public Integer getSuperficieInteressata() {
		return superficieInteressata;
	}
	public void setSuperficieInteressata(Integer superficieInteressata) {
		this.superficieInteressata = superficieInteressata;
	}
	public String getStatoIntervento() {
		return statoIntervento;
	}
	public void setStatoIntervento(String statoIntervento) {
		this.statoIntervento = statoIntervento;
	}
	public Integer getNumeroPiante() {
		return numeroPiante;
	}
	public void setNumeroPiante(Integer numeroPiante) {
		this.numeroPiante = numeroPiante;
	}
	public Integer getM3Prelevati() {
		return m3Prelevati;
	}
	public void setM3Prelevati(Integer m3Prelevati) {
		this.m3Prelevati = m3Prelevati;
	}
	public String getFasciaAltimetrica() {
		return fasciaAltimetrica;
	}
	public void setFasciaAltimetrica(String fasciaAltimetrica) {
		this.fasciaAltimetrica = fasciaAltimetrica;
	}
	public String getFinalitaTaglio() {
		return finalitaTaglio;
	}
	public void setFinalitaTaglio(String finalitaTaglio) {
		this.finalitaTaglio = finalitaTaglio;
	}
	public String getDestLegname() {
		return destLegname;
	}
	public void setDestLegname(String destLegname) {
		this.destLegname = destLegname;
	}
	public Integer getProgressivoIntervento() {
		return progressivoIntervento;
	}
	public String getEventoCorrelato() {
		return eventoCorrelato;
	}
	public void setProgressivoIntervento(Integer progressivoIntervento) {
		this.progressivoIntervento = progressivoIntervento;
	}
	public void setEventoCorrelato(String eventoCorrelato) {
		this.eventoCorrelato = eventoCorrelato;
	}
	@Override
	public String toString() {
		return "TipoInterventoDettaglio [conformeDeroga=" + conformeDeroga + ", annataSilvana=" + annataSilvana
				+ ", idParticelaForestale=" + Arrays.toString(idParticelaForestale) + ", progressivoIntervento="
				+ progressivoIntervento + ", dataPresuntaIntervento=" + dataPresuntaIntervento
				+ ", dataInizioIntervento=" + dataInizioIntervento + ", dataFineIntervento=" + dataFineIntervento
				+ ", eventoCorrelato=" + eventoCorrelato + ", governo=" + governo + ", tipoIntervento=" + tipoIntervento
				+ ", richiedePiedilsta=" + richiedePiedilsta + ", descrizione=" + descrizione + ", localita=" + localita
				+ ", superficieInteressata=" + superficieInteressata + ", statoIntervento=" + statoIntervento
				+ ", numeroPiante=" + numeroPiante + ", m3Prelevati=" + m3Prelevati + ", fasciaAltimetrica="
				+ fasciaAltimetrica + ", finalitaTaglio=" + finalitaTaglio + ", destLegname=" + destLegname + "]";
	}
	

	
	
	
}
