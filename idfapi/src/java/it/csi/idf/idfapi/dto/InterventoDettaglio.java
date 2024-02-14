/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class InterventoDettaglio {

	private TipoInterventoDettaglio tipoInterventoDettaglio;
	private RicadenzaInfo ricadenzaInfo;
	private List<DichPropCatasto> propCatastos;
	private List<SpecieInterventoVolumes> species;
	private DettagliDiTaglio dettagliDiTaglio;

	public TipoInterventoDettaglio getTipoInterventoDettaglio() {
		return tipoInterventoDettaglio;
	}

	public void setTipoInterventoDettaglio(TipoInterventoDettaglio tipoInterventoDettaglio) {
		this.tipoInterventoDettaglio = tipoInterventoDettaglio;
	}

	public RicadenzaInfo getRicadenzaInfo() {
		return ricadenzaInfo;
	}

	public void setRicadenzaInfo(RicadenzaInfo ricadenzaInfo) {
		this.ricadenzaInfo = ricadenzaInfo;
	}

	public List<DichPropCatasto> getPropCatastos() {
		return propCatastos;
	}

	public void setPropCatastos(List<DichPropCatasto> propCatastos) {
		this.propCatastos = propCatastos;
	}

	public List<SpecieInterventoVolumes> getSpecies() {
		return species;
	}

	public void setSpecies(List<SpecieInterventoVolumes> species) {
		this.species = species;
	}

	public DettagliDiTaglio getDettagliDiTaglio() {
		return dettagliDiTaglio;
	}

	public void setDettagliDiTaglio(DettagliDiTaglio dettagliDiTaglio) {
		this.dettagliDiTaglio = dettagliDiTaglio;
	}

}
