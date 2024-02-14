/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class InfoVarianteProroga {

	private Integer idPadreProroga;
	private Integer idPadreVariante;
	private Integer numProroghe;
	private Integer numVarianti;
	
	public Integer getIdPadreProroga() {
		return idPadreProroga;
	}
	public Integer getIdPadreVariante() {
		return idPadreVariante;
	}
	public Integer getNumProroghe() {
		return numProroghe;
	}
	public Integer getNumVarianti() {
		return numVarianti;
	}
	public void setIdPadreProroga(Integer idPadreProroga) {
		this.idPadreProroga = idPadreProroga;
	}
	public void setIdPadreVariante(Integer idPadreVariante) {
		this.idPadreVariante = idPadreVariante;
	}
	public void setNumProroghe(Integer numProroghe) {
		this.numProroghe = numProroghe;
	}
	public void setNumVarianti(Integer numVarianti) {
		this.numVarianti = numVarianti;
	}
	
}
