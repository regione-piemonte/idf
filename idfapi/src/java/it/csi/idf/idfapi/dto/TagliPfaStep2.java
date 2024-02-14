/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import it.csi.idf.idfapi.util.TipoUtilizzatoreTagliEnum;

public class TagliPfaStep2 {

	private Integer idIntervento;
	private Integer tipoIstanzaId;
	private String tipoIstanzaDescr;

	private String tipoAccreditamento;

	private Integer tipoRichiedenteId;
	private PersonaFisGiu soggetto;


	private TipoUtilizzatoreTagliEnum tipoUtilizzatore;

	private PersonaFisGiu utilizzatore;

	private Integer gestoreId;


	private Integer fkCategoriaIntervento;



	public Integer getIdIntervento() {
		return idIntervento;
	}

	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}


	public Integer getTipoIstanzaId() {
		return tipoIstanzaId;
	}

	public void setTipoIstanzaId(Integer tipoIstanzaId) {
		this.tipoIstanzaId = tipoIstanzaId;
	}

	public String getTipoIstanzaDescr() {
		return tipoIstanzaDescr;
	}

	public void setTipoIstanzaDescr(String tipoIstanzaDescr) {
		this.tipoIstanzaDescr = tipoIstanzaDescr;
	}



	public Integer getTipoRichiedenteId() {
		return tipoRichiedenteId;
	}

	public void setTipoRichiedenteId(Integer tipoRichiedenteId) {
		this.tipoRichiedenteId = tipoRichiedenteId;
	}



	public TipoUtilizzatoreTagliEnum getTipoUtilizzatore() {
		return tipoUtilizzatore;
	}

	public void setTipoUtilizzatore(TipoUtilizzatoreTagliEnum tipoUtilizzatore) {
		this.tipoUtilizzatore = tipoUtilizzatore;
	}

	public PersonaFisGiu getUtilizzatore() {
		return utilizzatore;
	}

	public void setUtilizzatore(PersonaFisGiu utilizzatore) {
		this.utilizzatore = utilizzatore;
	}

	public PersonaFisGiu getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(PersonaFisGiu soggetto) {
		this.soggetto = soggetto;
	}


	public String getTipoAccreditamento() {
		return tipoAccreditamento;
	}

	public void setTipoAccreditamento(String tipoAccreditamento) {
		this.tipoAccreditamento = tipoAccreditamento;
	}


	public Integer getGestoreId() {
		return gestoreId;
	}

	public void setGestoreId(Integer gestoreId) {
		this.gestoreId = gestoreId;
	}

	public Integer getFkCategoriaIntervento() {
		return fkCategoriaIntervento;
	}

	public void setFkCategoriaIntervento(Integer fkCategoriaIntervento) {
		this.fkCategoriaIntervento = fkCategoriaIntervento;
	}


}
