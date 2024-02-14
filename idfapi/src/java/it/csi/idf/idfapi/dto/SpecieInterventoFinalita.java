/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class SpecieInterventoFinalita {

	private Integer idSpecie;
	private Integer idIntervento;
	private Integer idFinalitaTaglio;
	private Float percAutoconsumo;
	private Float percCommerciale;
	private String dataInserimento;
	private String dataAggiornamento;
	private String flgSpeciePriorita;

	private String descrFinalitaTaglio;


	public Integer getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}

	public Integer getIdIntervento() {
		return idIntervento;
	}

	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}

	public Integer getIdFinalitaTaglio() {
		return idFinalitaTaglio;
	}

	public void setIdFinalitaTaglio(Integer idFinalitaTaglio) {
		this.idFinalitaTaglio = idFinalitaTaglio;
	}

	public Float getPercAutoconsumo() {
		return percAutoconsumo;
	}

	public void setPercAutoconsumo(Float percAutoconsumo) {
		this.percAutoconsumo = percAutoconsumo;
	}

	public Float getPercCommerciale() {
		return percCommerciale;
	}

	public void setPercCommerciale(Float percCommerciale) {
		this.percCommerciale = percCommerciale;
	}

	public String getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(String dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public String getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(String dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public String getFlgSpeciePriorita() {
		return flgSpeciePriorita;
	}

	public void setFlgSpeciePriorita(String flgSpeciePriorita) {
		this.flgSpeciePriorita = flgSpeciePriorita;
	}

	public String getDescrFinalitaTaglio() {
		return descrFinalitaTaglio;
	}

	public void setDescrFinalitaTaglio(String descrFinalitaTaglio) {
		this.descrFinalitaTaglio = descrFinalitaTaglio;
	}
}
