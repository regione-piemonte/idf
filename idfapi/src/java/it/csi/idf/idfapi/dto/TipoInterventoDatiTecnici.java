/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;
import it.csi.idf.idfapi.util.ConformeDerogaEnum;

/*
 * This is a model for the first accordion in second step 
 * of creation of intervention in PFA application
 */

public class TipoInterventoDatiTecnici {
	
	private ConformeDerogaEnum conformeDeroga;
	private Integer progressivoNumerico;
	private Integer fkStatoIntervento;
	private LocalDate dataPresuntaIntervento;
	private String annataSilvana;
	private Integer[] idParticelaForestale;
	private Integer idEventoCorelato;
	private Integer fkGoverno;
	private Byte richiedePiedilsta;
	private String descrizione;
	private String localita;
	private BigDecimal superficieInteressata;
	private Integer fkTipoIntervento;
	private Integer fasciaAltimetrica;
	private Integer fkFinalitaTaglio;
	private Integer fkDestLegname;
	
	public ConformeDerogaEnum getConformeDeroga() {
		return conformeDeroga;
	}
	public void setConformeDeroga(ConformeDerogaEnum conformeDeroga) {
		this.conformeDeroga = conformeDeroga;
	}
	public Integer getProgressivoNumerico() {
		return progressivoNumerico;
	}
	public void setProgressivoNumerico(Integer progressivoNumerico) {
		this.progressivoNumerico = progressivoNumerico;
	}
	
	public Integer getFkStatoIntervento() {
		return fkStatoIntervento;
	}
	public void setFkStatoIntervento(Integer fkStatoIntervento) {
		this.fkStatoIntervento = fkStatoIntervento;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataPresuntaIntervento() {
		return dataPresuntaIntervento;
	}
	
	public void setDataPresuntaIntervento(LocalDate dataPresuntaIntervento) {
		this.dataPresuntaIntervento = dataPresuntaIntervento;
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
	
	public Integer getFkGoverno() {
		return fkGoverno;
	}
	public void setFkGoverno(Integer fkGoverno) {
		this.fkGoverno = fkGoverno;
	}
	public Byte getRichiedePiedilsta() {
		return richiedePiedilsta;
	}
	public void setRichiedePiedilsta(Byte richiedePiedilsta) {
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
	public BigDecimal getSuperficieInteressata() {
		return superficieInteressata;
	}
	public void setSuperficieInteressata(BigDecimal superficieInteressata) {
		this.superficieInteressata = superficieInteressata;
	}
	public Integer getFkTipoIntervento() {
		return fkTipoIntervento;
	}
	public void setFkTipoIntervento(Integer fkTipoIntervento) {
		this.fkTipoIntervento = fkTipoIntervento;
	}
	public Integer getFasciaAltimetrica() {
		return fasciaAltimetrica;
	}
	public void setFasciaAltimetrica(Integer fasciaAltimetrica) {
		this.fasciaAltimetrica = fasciaAltimetrica;
	}
	public Integer getFkFinalitaTaglio() {
		return fkFinalitaTaglio;
	}
	public void setFkFinalitaTaglio(Integer fkFinalitaTaglio) {
		this.fkFinalitaTaglio = fkFinalitaTaglio;
	}
	public Integer getFkDestLegname() {
		return fkDestLegname;
	}
	public void setFkDestLegname(Integer fkDestLegname) {
		this.fkDestLegname = fkDestLegname;
	}
	
	
	public Integer getIdEventoCorelato() {
		return idEventoCorelato;
	}
	public void setIdEventoCorelato(Integer idEventoCorelato) {
		this.idEventoCorelato = idEventoCorelato;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoInterventoDatiTecnici [conformeDeroga=");
		builder.append(conformeDeroga);
		builder.append(", progressivoNumerico=");
		builder.append(progressivoNumerico);
		builder.append(", fkStatoIntervento=");
		builder.append(fkStatoIntervento);
		builder.append(", dataPresuntaIntervento=");
		builder.append(dataPresuntaIntervento);
		builder.append(", annataSilvana=");
		builder.append(annataSilvana);
		builder.append(", idParticelaForestale=");
		builder.append(Arrays.toString(idParticelaForestale));
		builder.append(", idEventoCorelato=");
		builder.append(idEventoCorelato);
		builder.append(", fkGoverno=");
		builder.append(fkGoverno);
		builder.append(", richiedePiedilsta=");
		builder.append(richiedePiedilsta);
		builder.append(", descrizione=");
		builder.append(descrizione);
		builder.append(", localita=");
		builder.append(localita);
		builder.append(", superficieInteressata=");
		builder.append(superficieInteressata);
		builder.append(", fkTipoIntervento=");
		builder.append(fkTipoIntervento);
		builder.append(", fasciaAltimetrica=");
		builder.append(fasciaAltimetrica);
		builder.append(", fkFinalitaTaglio=");
		builder.append(fkFinalitaTaglio);
		builder.append(", fkDestLegname=");
		builder.append(fkDestLegname);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
