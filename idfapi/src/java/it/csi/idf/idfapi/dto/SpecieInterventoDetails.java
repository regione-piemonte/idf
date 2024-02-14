/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class SpecieInterventoDetails {

	private List<SpecieInterventoVolumes> species;
	private DettagliDiTaglio dettagliDiTaglio;

	public List<SpecieInterventoVolumes> getSpecies() {
		return species;
	}

	public void setSpecies(List<SpecieInterventoVolumes> species) {
		this.species = species;
	}

	public DettagliDiTaglio getDettagliDitaglio() {
		return dettagliDiTaglio;
	}

	public void setDettagliDitaglio(DettagliDiTaglio dettagliDiTaglio) {
		this.dettagliDiTaglio = dettagliDiTaglio;
	}

}
