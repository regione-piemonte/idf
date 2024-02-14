/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class PartforInterventoDTO {

	private Integer idIntervento;
	private Integer idGeoPlParticellaForestale;
	private LocalDate dataInizioValidita;
	
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public Integer getIdGeoPlParticellaForestale() {
		return idGeoPlParticellaForestale;
	}
	public void setIdGeoPlParticellaForestale(Integer idGeoPlParticellaForestale) {
		this.idGeoPlParticellaForestale = idGeoPlParticellaForestale;
	}
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	
	
}
