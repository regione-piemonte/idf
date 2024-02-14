/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.StringJoiner;

public class PlainSoggettoIstanzaTagli {


	private Integer idIntervento;
	private String nrIstanza;
	private Integer nrIstanzaForestale;

	private String descrizioneTipoIstanza;
	private String richiedente;

	private SoggettoResource soggettoRichiedente;
	private ComuneResource comune;

	private String tipoIntervento;
	private String descrizioneIntervento;
	private LocalDate dataInserimento;
	private String stato;
	private int idStato;
	private Integer idTipoIstanza;

	public Integer getIdIntervento() {
		return idIntervento;
	}

	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}

	public String getNrIstanza() {
		return nrIstanza;
	}

	public void setNrIstanza(String nrIstanza) {
		this.nrIstanza = nrIstanza;
	}

	public String getDescrizioneTipoIstanza() {
		return descrizioneTipoIstanza;
	}

	public void setDescrizioneTipoIstanza(String descrizioneTipoIstanza) {
		this.descrizioneTipoIstanza = descrizioneTipoIstanza;
	}

	public String getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}

	public SoggettoResource getSoggettoRichiedente() {
		return soggettoRichiedente;
	}

	public void setSoggettoRichiedente(SoggettoResource soggettoRichiedente) {
		this.soggettoRichiedente = soggettoRichiedente;
	}

	public ComuneResource getComune() {
		return comune;
	}

	public void setComune(ComuneResource comune) {
		this.comune = comune;
	}

	public String getTipoIntervento() {
		return tipoIntervento;
	}

	public void setTipoIntervento(String tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}

	public String getDescrizioneIntervento() {
		return descrizioneIntervento;
	}

	public void setDescrizioneIntervento(String descrizioneIntervento) {
		this.descrizioneIntervento = descrizioneIntervento;
	}

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Integer getNrIstanzaForestale() {
		return nrIstanzaForestale;
	}

	public void setNrIstanzaForestale(Integer nrIstanzaForestale) {
		this.nrIstanzaForestale = nrIstanzaForestale;
	}

	public int getIdStato() {
		return idStato;
	}

	public void setIdStato(int idStato) {
		this.idStato = idStato;
	}
	

	public Integer getIdTipoIstanza() {
		return idTipoIstanza;
	}

	public void setIdTipoIstanza(Integer idTipoIstanza) {
		this.idTipoIstanza = idTipoIstanza;
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", PlainSoggettoIstanzaTagli.class.getSimpleName() + "[", "]")
				.add("idIntervento=" + idIntervento)
				.add("nrIstanza='" + nrIstanza + "'")
				.add("nrIstanzaForestale=" + nrIstanzaForestale)
				.add("descrizioneTipoIstanza='" + descrizioneTipoIstanza + "'")
				.add("richiedente='" + richiedente + "'")
				.add("soggettoRichiedente=" + soggettoRichiedente)
				.add("comune=" + comune)
				.add("tipoIntervento='" + tipoIntervento + "'")
				.add("descrizioneIntervento='" + descrizioneIntervento + "'")
				.add("dataInserimento=" + dataInserimento)
				.add("stato='" + stato + "'")				
				.add("idStato='" + idStato + "'")
				.add("idTipoIstanza='" + idTipoIstanza + "'")
				.toString();
	}
}


