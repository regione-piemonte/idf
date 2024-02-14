/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class DescrizioneIntervento {

	private String descrizione;
	private String governo;
	private String tipoDiIntervento;
	private String categorieForestali;
	private String localita;
	private String flgPiedilista;
	private Integer numeroPiante;
	private String finalitaDelTaglio;
	private String DestinazioneDelLegname;
	private Integer ramaglia;
	private Integer stimaMassaRetraibileM3;
	private String viabilita;
	private String TipoDiEsbosco;
	private String noteEsbosco;
	private String fasciaAltimetrica;
	private String specieInteresate;
	private Integer supTagliata;
	private BigDecimal supInteressata;
	private BigDecimal supCatastale;

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getGoverno() {
		return governo;
	}

	public void setGoverno(String governo) {
		this.governo = governo;
	}

	public String getTipoDiIntervento() {
		return tipoDiIntervento;
	}

	public void setTipoDiIntervento(String tipoDiIntervento) {
		this.tipoDiIntervento = tipoDiIntervento;
	}

	public String getCategorieForestali() {
		return categorieForestali;
	}

	public void setCategorieForestali(String categorieForestali) {
		this.categorieForestali = categorieForestali;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getFlgPiedilista() {
		return flgPiedilista;
	}

	public void setFlgPiedilista(String flgPiedilista) {
		this.flgPiedilista = flgPiedilista;
	}

	public Integer getNumeroPiante() {
		return numeroPiante;
	}

	public void setNumeroPiante(Integer numeroPiante) {
		this.numeroPiante = numeroPiante;
	}

	public String getFinalitaDelTaglio() {
		return finalitaDelTaglio;
	}

	public void setFinalitaDelTaglio(String finalitaDelTaglio) {
		this.finalitaDelTaglio = finalitaDelTaglio;
	}

	public String getDestinazioneDelLegname() {
		return DestinazioneDelLegname;
	}

	public void setDestinazioneDelLegname(String destinazioneDelLegname) {
		DestinazioneDelLegname = destinazioneDelLegname;
	}

	public Integer getRamaglia() {
		return ramaglia;
	}

	public void setRamaglia(Integer ramaglia) {
		this.ramaglia = ramaglia;
	}

	public Integer getStimaMassaRetraibileM3() {
		return stimaMassaRetraibileM3;
	}

	public void setStimaMassaRetraibileM3(Integer stimaMassaRetraibileM3) {
		this.stimaMassaRetraibileM3 = stimaMassaRetraibileM3;
	}

	public String getViabilita() {
		return viabilita;
	}

	public void setViabilita(String viabilita) {
		this.viabilita = viabilita;
	}

	public String getTipoDiEsbosco() {
		return TipoDiEsbosco;
	}

	public void setTipoDiEsbosco(String tipoDiEsbosco) {
		TipoDiEsbosco = tipoDiEsbosco;
	}

	public String getNoteEsbosco() {
		return noteEsbosco;
	}

	public void setNoteEsbosco(String noteEsbosco) {
		this.noteEsbosco = noteEsbosco;
	}

	public String getFasciaAltimetrica() {
		return fasciaAltimetrica;
	}

	public void setFasciaAltimetrica(String fasciaAltimetrica) {
		this.fasciaAltimetrica = fasciaAltimetrica;
	}

	public String getSpecieInteresate() {
		return specieInteresate;
	}

	public void setSpecieInteresate(String specieInteresate) {
		this.specieInteresate = specieInteresate;
	}

	public Integer getSupTagliata() {
		return supTagliata;
	}

	public void setSupTagliata(Integer supTagliata) {
		this.supTagliata = supTagliata;
	}

	public BigDecimal getSupInteressata() {
		return supInteressata;
	}

	public void setSupInteressata(BigDecimal supInteressata) {
		this.supInteressata = supInteressata;
	}

	public BigDecimal getSupCatastale() {
		return supCatastale;
	}

	public void setSupCatastale(BigDecimal supCatastale) {
		this.supCatastale = supCatastale;
	}
	
}
