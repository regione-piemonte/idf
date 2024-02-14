/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class StatoAds {
	private Integer idStatoAds;
	private String descrStatoAds;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	public Integer getIdStatoAds() {
		return idStatoAds;
	}
	public void setIdStatoAds(Integer idStatoAds) {
		this.idStatoAds = idStatoAds;
	}
	public String getDescrStatoAds() {
		return descrStatoAds;
	}
	public void setDescrStatoAds(String descrStatoAds) {
		this.descrStatoAds = descrStatoAds;
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
