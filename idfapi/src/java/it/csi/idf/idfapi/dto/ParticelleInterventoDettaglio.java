/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class ParticelleInterventoDettaglio {

	private List<DichPropCatasto> propCatastos;
	private List<RicadenzaInformazioni> ricadenzaAreeProtette;
	private List<RicadenzaInformazioni> ricadenzaNatura2000;
	private List<RicadenzaInformazioni> ricadenzaPopolamentiDaSeme;
	private List<RicadenzaInformazioni> ricadenzaCategorieForestali;

	public List<DichPropCatasto> getPropCatastos() {
		return propCatastos;
	}
	public void setPropCatastos(List<DichPropCatasto> propCatastos) {
		this.propCatastos = propCatastos;
	}
	public List<RicadenzaInformazioni> getRicadenzaAreeProtette() {
		return ricadenzaAreeProtette;
	}
	public List<RicadenzaInformazioni> getRicadenzaNatura2000() {
		return ricadenzaNatura2000;
	}
	public List<RicadenzaInformazioni> getRicadenzaPopolamentiDaSeme() {
		return ricadenzaPopolamentiDaSeme;
	}
	public List<RicadenzaInformazioni> getRicadenzaCategorieForestali() {
		return ricadenzaCategorieForestali;
	}
	public void setRicadenzaAreeProtette(List<RicadenzaInformazioni> ricadenzaAreeProtette) {
		this.ricadenzaAreeProtette = ricadenzaAreeProtette;
	}
	public void setRicadenzaNatura2000(List<RicadenzaInformazioni> ricadenzaNatura2000) {
		this.ricadenzaNatura2000 = ricadenzaNatura2000;
	}
	public void setRicadenzaPopolamentiDaSeme(List<RicadenzaInformazioni> ricadenzaPopolamentiDaSeme) {
		this.ricadenzaPopolamentiDaSeme = ricadenzaPopolamentiDaSeme;
	}
	public void setRicadenzaCategorieForestali(List<RicadenzaInformazioni> ricadenzaCategorieForestali) {
		this.ricadenzaCategorieForestali = ricadenzaCategorieForestali;
	}
	
	
}
