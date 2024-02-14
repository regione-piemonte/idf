/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

public class SpeciePfaIntervento {

	private Integer idSpecie;
	private Integer idIntervento;
	private String flgSpeciePriorita;
	private Float volumeSpecia;
	private String dataInserimento;
	private String dataAggiornamento;
	private Integer fkUdm;
	private Integer numPiante;

	private TSpecie specie;

	private List<SpecieInterventoFinalita> specieFinalita;

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
	public String getFlgSpeciePriorita() {
		return flgSpeciePriorita;
	}
	public void setFlgSpeciePriorita(String flgSpeciePriorita) {
		this.flgSpeciePriorita = flgSpeciePriorita;
	}
	public Float getVolumeSpecia() {
		return volumeSpecia;
	}
	public void setVolumeSpecia(Float volumeSpecia) {
		this.volumeSpecia = volumeSpecia;
	}
	public Integer getFkUdm() {
		return fkUdm;
	}
	public void setFkUdm(Integer fkUdm) {
		this.fkUdm = fkUdm;
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

	public Integer getNumPiante() {
		return numPiante;
	}

	public void setNumPiante(Integer numPiante) {
		this.numPiante = numPiante;
	}

	public List<SpecieInterventoFinalita> getSpecieFinalita() {
		return specieFinalita;
	}

	public void setSpecieFinalita(List<SpecieInterventoFinalita> specieFinalita) {
		this.specieFinalita = specieFinalita;
	}

	public TSpecie getSpecie() {
		return specie;
	}

	public void setSpecie(TSpecie specie) {
		this.specie = specie;
	}
}
