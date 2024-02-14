/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class RicadenzaInfo {

	private String areeProtette;
	private String[] reteNatura2000;
	private String popolamentoSeme;
	private String categoriaForestali;

	public String getAreeProtette() {
		return areeProtette;
	}

	public void setAreeProtette(String areeProtette) {
		this.areeProtette = areeProtette;
	}

	public String[] getReteNatura2000() {
		return reteNatura2000;
	}

	public void setReteNatura2000(String[] reteNatura2000) {
		this.reteNatura2000 = reteNatura2000;
	}

	public String getPopolamentoSeme() {
		return popolamentoSeme;
	}

	public void setPopolamentoSeme(String popolamentoSeme) {
		this.popolamentoSeme = popolamentoSeme;
	}

	public String getCategoriaForestali() {
		return categoriaForestali;
	}

	public void setCategoriaForestali(String categoriaForestali) {
		this.categoriaForestali = categoriaForestali;
	}

}
