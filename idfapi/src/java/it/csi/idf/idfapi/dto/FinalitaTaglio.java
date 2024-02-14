/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class FinalitaTaglio {

	private Integer idFinalitaTaglio;
	private String descrFinalitaTaglio;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	public Integer getIdFinalitaTaglio() {
		return idFinalitaTaglio;
	}
	public void setIdFinalitaTaglio(Integer idFinalitaTaglio) {
		this.idFinalitaTaglio = idFinalitaTaglio;
	}
	public String getDescrFinalitaTaglio() {
		return descrFinalitaTaglio;
	}
	public void setDescrFinalitaTaglio(String descrFinalitaTaglio) {
		this.descrFinalitaTaglio = descrFinalitaTaglio;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	
	
}
