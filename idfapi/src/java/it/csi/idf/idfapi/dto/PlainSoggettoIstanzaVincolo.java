/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class PlainSoggettoIstanzaVincolo {
	
	private Integer idIntervento;
	private Integer nrIstanzaForestale;
	private Integer idTipoIstanza;
	private String competenza;
	private LocalDate dataInvio;
	private SoggettoResource richiedente;
	private ComuneResource comune;
	private String stato;
	private Integer idStato;
	private String statoCauzione;
	private String varianteProroga;
	private String rimboschimento;
	
		
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public Integer getNrIstanzaForestale() {
		return nrIstanzaForestale;
	}
	public void setNrIstanzaForestale(Integer nrIstanzaForestale) {
		this.nrIstanzaForestale = nrIstanzaForestale;
	}
	public Integer getIdTipoIstanza() {
		return idTipoIstanza;
	}
	public void setIdTipoIstanza(Integer idTipoIstanza) {
		this.idTipoIstanza = idTipoIstanza;
	}
	public String getCompetenza() {
		return competenza;
	}
	public void setCompetenza(String competenza) {
		this.competenza = competenza;
	}
	public LocalDate getDataInvio() {
		return dataInvio;
	}
	public void setDataInvio(LocalDate dataInvio) {
		this.dataInvio = dataInvio;
	}
	public SoggettoResource getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(SoggettoResource richiedente) {
		this.richiedente = richiedente;
	}
	public ComuneResource getComune() {
		return comune;
	}
	public void setComune(ComuneResource comune) {
		this.comune = comune;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Integer getIdStato() {
		return idStato;
	}
	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}
	public String getStatoCauzione() {
		return statoCauzione;
	}
	public void setStatoCauzione(String statoCauzione) {
		this.statoCauzione = statoCauzione;
	}
	public String getVarianteProroga() {
		return varianteProroga;
	}
	public void setVarianteProroga(String varianteProroga) {
		this.varianteProroga = varianteProroga;
	}
	public String getRimboschimento() {
		return rimboschimento;
	}
	public void setRimboschimento(String rimboschimento) {
		this.rimboschimento = rimboschimento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainSoggettoIstanzaVincolo [idIntervento=");
		builder.append(idIntervento);
		builder.append(", nrIstanzaForestale=");
		builder.append(nrIstanzaForestale);		
		builder.append(", idTipoIstanza=");
		builder.append(idTipoIstanza);		
		builder.append(", competenza=");
		builder.append(competenza);
		builder.append(", dataInvio=");
		builder.append(dataInvio);
		builder.append(", richiedente=");
		builder.append(richiedente);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", stato=");
		builder.append(stato);
		builder.append(", idStato=");
		builder.append(idStato);		
		builder.append(", statoCauzione=");
		builder.append(statoCauzione);
		builder.append(", varianteProroga=");
		builder.append(varianteProroga);
		builder.append(", rimboschimento=");
		builder.append(rimboschimento);
		builder.append("]");
		return builder.toString();
	}
	
	
}
