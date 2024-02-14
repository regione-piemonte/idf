/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import it.csi.idf.idfapi.util.TipoIstanzaEnum;

import java.util.List;

public class TagliPfaStep5 {


	private IntervPfaFat intervPfaFat;
	private Intervento intervento;

	private TipoIstanzaEnum tipoIstanza;
	private Integer tipoIstanzaId;
	private TipoIstanzaEnum tipoIstanzaProposta;
	private List<DocumentoAllegato> documentazioneAllegata;
	private List<FatDocumentoAllegato> allegati;
	private PersonaFisGiu tecnicoForestale;
    private String motivazione;
    private String noteFinaliRichiedente;

	public String getNoteFinaliRichiedente() {
		return noteFinaliRichiedente;
	}

	public void setNoteFinaliRichiedente(String noteFinaliRichiedente) {
		this.noteFinaliRichiedente = noteFinaliRichiedente;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public IntervPfaFat getIntervPfaFat() {
		return intervPfaFat;
	}

	public void setIntervPfaFat(IntervPfaFat intervPfaFat) {
		this.intervPfaFat = intervPfaFat;
	}

	public Intervento getIntervento() {
		return intervento;
	}

	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
	}

	public TipoIstanzaEnum getTipoIstanza() {
		return tipoIstanza;
	}

	public void setTipoIstanza(TipoIstanzaEnum tipoIstanza) {
		this.tipoIstanza = tipoIstanza;
	}

	public List<DocumentoAllegato> getDocumentazioneAllegata() {
		return documentazioneAllegata;
	}

	public void setDocumentazioneAllegata(List<DocumentoAllegato> documentazioneAllegata) {
		this.documentazioneAllegata = documentazioneAllegata;
	}

	public List<FatDocumentoAllegato> getAllegati() {
		return allegati;
	}

	public void setAllegati(List<FatDocumentoAllegato> allegati) {
		this.allegati = allegati;
	}

	public PersonaFisGiu getTecnicoForestale() {
		return tecnicoForestale;
	}

	public void setTecnicoForestale(PersonaFisGiu tecnicoForestale) {
		this.tecnicoForestale = tecnicoForestale;
	}

	public TipoIstanzaEnum getTipoIstanzaProposta() {
		return tipoIstanzaProposta;
	}

	public void setTipoIstanzaProposta(TipoIstanzaEnum tipoIstanzaProposta) {
		this.tipoIstanzaProposta = tipoIstanzaProposta;
	}

	public Integer getTipoIstanzaId() {
		return tipoIstanzaId;
	}

	public void setTipoIstanzaId(Integer tipoIstanzaId) {
		this.tipoIstanzaId = tipoIstanzaId;
	}
}

